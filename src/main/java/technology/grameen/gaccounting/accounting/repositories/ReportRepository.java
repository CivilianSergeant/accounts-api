package technology.grameen.gaccounting.accounting.repositories;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.projection.LedgerTransaction;
import technology.grameen.gaccounting.projection.ReportData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Voucher, Long> {

    @Query(value = "SELECT p.alias, CASE WHEN g1.TITLE IS NULL THEN g.TITLE ELSE (g1.CODE || ' ' || g1.TITLE) END AS primaryGroup,(g.code || ' ' || g.TITLE) AS subGroup, (p.code || ' ' || p.TITLE) AS ledgerAcc, p.transaction_Type AS transType, p.id,\n" +
            "\t NVL(p.OPENING_BALANCE,0) AS openingBalance,  NVL(p.OPENING_CREDIT_BALANCE,0) openingCreditBalance,\n" +
            "            CASE \n" +
            "            WHEN p.alias = 'asset' THEN \n" +
            "            \t(p.OPENING_BALANCE+(SUM(DEBIT)-SUM(CREDIT))) \n" +
            "            WHEN p.alias = 'drawings' THEN  \n" +
            "            \t(p.OPENING_BALANCE+(SUM(DEBIT)-SUM(CREDIT) ))\n" +
            "            WHEN p.alias = 'expense' THEN  \n" +
            "            \t(p.OPENING_BALANCE+ (SUM(DEBIT)-SUM(CREDIT))) \n" +
            "            WHEN p.alias = 'income' THEN  \n" +
            "            \t(p.OPENING_BALANCE+(SUM(CREDIT)-SUM(DEBIT) ))\n" +
            "            WHEN p.alias = 'capital' THEN  \n" +
            "            \t(p.OPENING_BALANCE+(SUM(CREDIT)-SUM(DEBIT) ))\n" +
            "            WHEN p.alias = 'liabilities' THEN  \n" +
            "            \t(p.OPENING_BALANCE+(SUM(CREDIT)-SUM(DEBIT) ))\n" +
            "            END AS BALANCE, \n" +
            "            SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM (SELECT t.TRANSACTION_DATE, t.TRANSACTION_TYPE,  ca.ID, ca.PARENT_ID,cal.OPENING_BALANCE, \n" +
            "            cal.OPENING_CREDIT_BALANCE,\n" +
            "            cat.ALIAS, TITLE, ca.CODE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, \n" +
            "                        NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit \n" +
            "                        FROM CA_LEDGERS cal \n" +
            "                        LEFT JOIN TRANSACTIONS t ON t.CHART_ACCOUNT_ID = cal.CHART_ACCOUNT_ID\n" +
            "                        JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID  \n" +
            "                        JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID  \n" +
            "\t\t\t              WHERE (t.TRANSACTION_DATE BETWEEN TO_DATE(:yearStart,'YYYY-MM-DD') AND TO_DATE(:yearEnd,'yyyy-mm-dd') )\n" +
            "                        GROUP BY t.TRANSACTION_DATE, t.TRANSACTION_TYPE, ca.ID, ca.PARENT_ID, TITLE, ca.CODE, TRANSACTION_TYPE, cat.ALIAS,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE) p\n" +
            "                        LEFT JOIN CHART_ACCOUNTS g ON g.ID = p.parent_ID\n" +
            "                        LEFT JOIN CHART_ACCOUNTS g1 ON g.PARENT_ID = g1.ID\n" +
            " WHERE p.transaction_Type IS NOT NULL "+
            "                        GROUP BY g.TITLE,g.code,g1.code,g1.TITLE, p.TITLE, p.code, p.ID, p.alias,p.OPENING_BALANCE,p.OPENING_CREDIT_BALANCE,p.transaction_Type\n" +
            "                        ORDER BY alias asc",nativeQuery = true)
    List<ReportData> getTrialBalance(@Param("yearStart") String yearStart, @Param("yearEnd") String yearEnd);


    @Query(value = "SELECT p.alias, CASE WHEN g1.TITLE IS NULL THEN (g.TITLE) ELSE (g1.CODE || ' ' || g1.TITLE) END AS primaryGroup,(g.code || ' ' || g.TITLE) AS subGroup,(p.CODE || ' ' ||p.TITLE) AS ledgerAcc, p.transaction_Type AS transType, p.id, \n" +
            "            NVL(p.OPENING_BALANCE,0) AS openingBalance,NVL(p.OPENING_CREDIT_BALANCE,0) AS openingCreditBalance, \n" +
            "            SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM ( \n" +
            "            SELECT  t.transaction_date, t.TRANSACTION_TYPE,  ca.ID, ca.code, ca.PARENT_ID,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE, cat.ALIAS, TITLE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, \n" +
            "                                    NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit \n" +
            "                                    FROM CA_LEDGERS cal  \n" +
            "                                    LEFT JOIN TRANSACTIONS t ON t.CHART_ACCOUNT_ID = cal.CHART_ACCOUNT_ID \n" +
            "                                    JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID \n" +
            "                                    JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID \n" +
            "             WHERE cat.alias = :caType AND (t.transaction_date BETWEEN to_date(:yearStart,'yyyy-mm-dd') AND to_date(:yearEnd,'yyyy-mm-dd'))\n" +
            "                                   GROUP BY t.transaction_date, t.TRANSACTION_TYPE, ca.ID, ca.PARENT_ID, ca.code, TITLE, TRANSACTION_TYPE, cat.ALIAS,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE) p \n" +
            "             LEFT JOIN CHART_ACCOUNTS g ON g.ID = p.parent_ID \n" +
            "                                    LEFT JOIN CHART_ACCOUNTS g1 ON g.PARENT_ID = g1.ID \n" +
            " WHERE p.transaction_Type IS NOT NULL "+
            "                                   GROUP BY g.TITLE,g1.TITLE, g.code,g1.code, p.code, p.TITLE, p.ID, p.alias,p.OPENING_BALANCE,p.OPENING_CREDIT_BALANCE,p.transaction_Type \n" +
            "                                   ORDER BY alias asc",
    nativeQuery = true)
    List<ReportData> getLedgerWiseTransactions(@Param("caType") String caType,@Param("yearStart") String yearStart,
                                               @Param("yearEnd") String yearEnd);


    @Query(value = "SELECT p.alias, CASE WHEN g1.TITLE IS NULL THEN g.TITLE ELSE (g1.CODE || ' ' || g1.TITLE) END AS primaryGroup, (g.CODE || ' ' || g.TITLE) AS subGroup," +
            " (p.CODE || ' ' || p.TITLE) AS ledgerAcc, p.transaction_Type AS transType, p.id, " +
            " NVL(p.OPENING_BALANCE,0) AS openingBalance,NVL(p.OPENING_CREDIT_BALANCE,0) AS openingCreditBalance, " +
            " SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM ( " +
            " SELECT  t.transaction_date, t.TRANSACTION_TYPE,  ca.ID, ca.PARENT_ID,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE, cat.ALIAS, TITLE, ca.CODE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, " +
            "                        NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit " +
            "                        FROM CA_LEDGERS cal  " +
            "                        LEFT JOIN TRANSACTIONS t ON t.CHART_ACCOUNT_ID = cal.CHART_ACCOUNT_ID " +
            "                        JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID " +
            "                        JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID " +
            " WHERE cat.alias IN :caTypes AND (t.transaction_date between to_date(:yearStart,'yyyy-mm-dd') AND to_date(:yearEnd,'yyyy-mm-dd'))" +
            "                        GROUP BY t.transaction_date, t.TRANSACTION_TYPE, ca.ID, ca.PARENT_ID, TITLE, ca.CODE, TRANSACTION_TYPE, cat.ALIAS,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE) p " +
            " LEFT JOIN CHART_ACCOUNTS g ON g.ID = p.parent_ID " +
            "                        LEFT JOIN CHART_ACCOUNTS g1 ON g.PARENT_ID = g1.ID " +
            " WHERE p.transaction_Type IS NOT NULL "+
            "                        GROUP BY g.TITLE, g.CODE,g1.TITLE,g1.CODE, p.TITLE,p.CODE, p.ID, p.alias,p.OPENING_BALANCE,p.OPENING_CREDIT_BALANCE,p.transaction_Type " +
            "                        ORDER BY alias asc",
            nativeQuery = true)
    List<ReportData> getLedgerWiseTransactionsByTypes(@Param("caTypes") List<String> caTypes,
                                                      @Param("yearStart") String yearStart,
                                                      @Param("yearEnd") String yearEnd);


    interface LedgerStatement{
        Long getVid();
        Long getTid();
        Long getReverseTrId();
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime getTransactionDate();
        String getTransactionType();
        String getReverseAccountTitle();
        String getAccountTitle();
        BigDecimal getLedgerAmount();
        String getReverseTransactionType();
        String getVoucherNo();
        BigDecimal getReverseAmount();
        String getVoucherType();
    }
    @Query(value = "SELECT v.id AS vid, t2.id as tid, a.tid as reverseTrId, t2.TRANSACTION_DATE AS transactionDate ,t2.TRANSACTION_TYPE AS transactionType,\n" +
            "a.title AS reverseAccountTitle ,ca3.title AS accountTitle ,t2.AMOUNT AS ledgerAmount , a.transaction_type AS reverseTransactionType,\n" +
            "v.voucher_no as voucherNo, a.amount AS reverseAmount, a.alias as voucherType FROM TRANSACTIONS t2 \n" +
            "JOIN CHART_ACCOUNTS ca3 ON ca3.id = t2.CHART_ACCOUNT_ID \n" +
            "JOIN VOUCHERS v ON v.id = t2.VOUCHER_ID \n" +
            "JOIN (\n" +
            "SELECT v2.id AS vid, t.id as tid, TRANSACTION_DATE, TRANSACTION_TYPE, ca2.title, vt.alias, amount FROM TRANSACTIONS t \n" +
            "JOIN CHART_ACCOUNTS ca2 ON ca2.id = t.CHART_ACCOUNT_ID\n" +
            "JOIN VOUCHERS v2 ON v2.id = t.VOUCHER_ID \n" +
            "JOIN VOUCHER_TYPES vt ON vt.id = v2.VOUCHER_TYPE_ID \n" +
            "WHERE t.VOUCHER_ID  IN (\n" +
            "SELECT v.id FROM TRANSACTIONS t \n" +
            "JOIN CHART_ACCOUNTS ca ON ca.ID = t.CHART_ACCOUNT_ID \n" +
            "JOIN VOUCHERS v ON v.id = t.VOUCHER_ID \n" +
            "WHERE ca.CODE = :code AND t.TRANSACTION_DATE BETWEEN :fromDate AND :toDate ) AND ca2.CODE <> :code\n" +
            "\t\tAND t.TRANSACTION_DATE BETWEEN :fromDate AND :toDate ORDER BY t.amount DESC \n" +
            ") a ON a.vid = t2.VOUCHER_ID \n" +
            "WHERE ca3.CODE = :code AND a.transaction_Type != t2.TRANSACTION_TYPE AND (v.VOUCHER_DATE BETWEEN :fromDate AND :toDate)\n" +
            "AND (t2.TRANSACTION_DATE BETWEEN :fromDate AND :toDate)",nativeQuery = true)
    List<LedgerStatement> getLedgerStatement(@Param("code") String code, @Param("fromDate") LocalDateTime fromDate,
                                             @Param("toDate") LocalDateTime toDate);



    @Query(value = "SELECT v2.id AS vid, t.id as tid,ct.ALIAS chartType, TRANSACTION_DATE transactionDate, " +
            "TRANSACTION_TYPE transactionType, ca2.title, vt.alias, amount\n" +
            "FROM TRANSACTIONS t \n" +
            "            JOIN CHART_ACCOUNTS ca2 ON ca2.id = t.CHART_ACCOUNT_ID\n" +
            "            JOIN CA_TYPES ct ON ct.ID  = ca2.CHART_ACCOUNT_TYPE_ID\n" +
            "            JOIN VOUCHERS v2 ON v2.id = t.VOUCHER_ID \n" +
            "            JOIN VOUCHER_TYPES vt ON vt.id = v2.VOUCHER_TYPE_ID \n" +
            "            WHERE t.VOUCHER_ID  IN ( SELECT v.id FROM TRANSACTIONS t \n" +
            "            JOIN CHART_ACCOUNTS ca ON ca.ID = t.CHART_ACCOUNT_ID\n" +
            "            JOIN VOUCHERS v ON v.id = t.VOUCHER_ID \n" +
            "            WHERE ca.CODE = :code AND t.TRANSACTION_DATE \n" +
            "            < :fromDate) \n" +
            "            AND ca2.CODE = :code\n" +
            "            AND t.TRANSACTION_DATE \n" +
            "            < :fromDate ORDER BY t.amount DESC",nativeQuery = true)
    List<LedgerTransaction> getLedgerTransactions(@Param("code") String code, @Param("fromDate") LocalDateTime fromDate);
}
