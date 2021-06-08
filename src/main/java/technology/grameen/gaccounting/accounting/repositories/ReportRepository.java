package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.projection.ReportData;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Voucher, Long> {

    @Query(value = "SELECT p.alias, g1.TITLE AS primaryGroup,g.TITLE AS subGroup,p.TITLE AS ledgerAcc, " +
            "p.transaction_Type AS transType,  p.id,\n" +
            "NVL(p.OPENING_BALANCE,0) AS openingBalance, NVL(p.opening_credit_balance,0) as openingCreditBalance, \n" +
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
            "            SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM (SELECT v.created_at, t.transaction_type,  ca.ID, ca.PARENT_ID,cal.OPENING_BALANCE, cal.opening_credit_balance, cat.ALIAS, TITLE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, \n" +
            "                        NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit \n" +
            "                        FROM CA_LEDGERS cal \n" +
            "                        LEFT JOIN TRANSACTIONS t ON t.CHART_ACCOUNT_ID = cal.CHART_ACCOUNT_ID\n" +
            "                        INNER JOIN VOUCHERS v ON  v.ID = t.VOUCHER_ID\n" +
            "                        JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID  \n" +
            "                        JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID  \n" +
            "                        GROUP BY v.created_at, t.transaction_type, ca.ID, ca.PARENT_ID, TITLE, TRANSACTION_TYPE, cat.ALIAS,cal.OPENING_BALANCE, cal.opening_credit_balance) p\n" +
            "                        LEFT JOIN CHART_ACCOUNTS g ON g.ID = p.parent_ID\n" +
            "                        LEFT JOIN CHART_ACCOUNTS g1 ON g.PARENT_ID = g1.ID\n" +
            "                        GROUP BY g.TITLE,g1.TITLE, p.TITLE, p.ID, p.alias,p.OPENING_BALANCE,p.opening_credit_balance,p.transaction_type\n" +
            "                        ORDER BY alias asc",nativeQuery = true)
    List<ReportData> getTrialBalance();


    @Query(value = "SELECT p.alias, g1.TITLE AS primaryGroup,g.TITLE AS subGroup,p.TITLE AS ledgerAcc, p.transaction_Type AS transType, p.id, " +
            " NVL(p.OPENING_BALANCE,0) AS openingBalance,NVL(p.OPENING_CREDIT_BALANCE,0) AS openingCreditBalance, " +
            " SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM ( " +
            " SELECT  v.created_at, t.TRANSACTION_TYPE,  ca.ID, ca.PARENT_ID,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE, cat.ALIAS, TITLE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, " +
            "                        NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit " +
            "                        FROM CA_LEDGERS cal  " +
            "                        LEFT JOIN TRANSACTIONS t ON t.CHART_ACCOUNT_ID = cal.CHART_ACCOUNT_ID " +
            "                        INNER JOIN VOUCHERS v ON  v.ID = t.VOUCHER_ID " +
            "                        JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID " +
            "                        JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID " +
            " WHERE cat.alias = :caType " +
            "                        GROUP BY v.created_at, t.TRANSACTION_TYPE, ca.ID, ca.PARENT_ID, TITLE, TRANSACTION_TYPE, cat.ALIAS,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE) p " +
            " LEFT JOIN CHART_ACCOUNTS g ON g.ID = p.parent_ID " +
            "                        LEFT JOIN CHART_ACCOUNTS g1 ON g.PARENT_ID = g1.ID " +
            "                        GROUP BY g.TITLE,g1.TITLE, p.TITLE, p.ID, p.alias,p.OPENING_BALANCE,p.OPENING_CREDIT_BALANCE,p.transaction_Type " +
            "                        ORDER BY alias asc",
    nativeQuery = true)
    List<ReportData> getLedgerWiseTransactions(@Param("caType") String caType);


    @Query(value = "SELECT p.alias, (g1.CODE || ' ' || g1.TITLE) AS primaryGroup, (g.CODE || ' ' || g.TITLE) AS subGroup," +
            " (p.CODE || ' ' || p.TITLE) AS ledgerAcc, p.transaction_Type AS transType, p.id, " +
            " NVL(p.OPENING_BALANCE,0) AS openingBalance,NVL(p.OPENING_CREDIT_BALANCE,0) AS openingCreditBalance, " +
            " SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM ( " +
            " SELECT  v.created_at, t.TRANSACTION_TYPE,  ca.ID, ca.PARENT_ID,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE, cat.ALIAS, TITLE, ca.CODE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, " +
            "                        NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit " +
            "                        FROM CA_LEDGERS cal  " +
            "                        LEFT JOIN TRANSACTIONS t ON t.CHART_ACCOUNT_ID = cal.CHART_ACCOUNT_ID " +
            "                        INNER JOIN VOUCHERS v ON  v.ID = t.VOUCHER_ID " +
            "                        JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID " +
            "                        JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID " +
            " WHERE cat.alias IN :caTypes " +
            "                        GROUP BY v.created_at, t.TRANSACTION_TYPE, ca.ID, ca.PARENT_ID, TITLE, ca.CODE, TRANSACTION_TYPE, cat.ALIAS,cal.OPENING_BALANCE,cal.OPENING_CREDIT_BALANCE) p " +
            " LEFT JOIN CHART_ACCOUNTS g ON g.ID = p.parent_ID " +
            "                        LEFT JOIN CHART_ACCOUNTS g1 ON g.PARENT_ID = g1.ID " +
            "                        GROUP BY g.TITLE, g.CODE,g1.TITLE,g1.CODE, p.TITLE,p.CODE, p.ID, p.alias,p.OPENING_BALANCE,p.OPENING_CREDIT_BALANCE,p.transaction_Type " +
            "                        ORDER BY alias asc",
            nativeQuery = true)
    List<ReportData> getLedgerWiseTransactionsByTypes(@Param("caTypes") List<String> caTypes);



}
