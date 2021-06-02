package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.projection.TrialBalance;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Voucher, Long> {

    @Query(value = "SELECT p.alias, g1.TITLE AS primaryGroup,g.TITLE AS subGroup,p.TITLE AS ledgerAcc, " +
            "p.transaction_Type AS transType,  p.id,\n" +
            "\tp.OPENING_BALANCE AS openingBalance, \n" +
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
            "            SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM (SELECT v.created_at, t.transaction_type,  ca.ID, ca.PARENT_ID,cal.OPENING_BALANCE, cat.ALIAS, TITLE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, \n" +
            "                        NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit \n" +
            "                        FROM CA_LEDGERS cal \n" +
            "                        LEFT JOIN TRANSACTIONS t ON t.CHART_ACCOUNT_ID = cal.CHART_ACCOUNT_ID\n" +
            "                        INNER JOIN VOUCHERS v ON  v.ID = t.VOUCHER_ID\n" +
            "                        JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID  \n" +
            "                        JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID  \n" +
            "                        GROUP BY v.created_at, t.transaction_type, ca.ID, ca.PARENT_ID, TITLE, TRANSACTION_TYPE, cat.ALIAS,cal.OPENING_BALANCE) p\n" +
            "                        LEFT JOIN CHART_ACCOUNTS g ON g.ID = p.parent_ID\n" +
            "                        LEFT JOIN CHART_ACCOUNTS g1 ON g.PARENT_ID = g1.ID\n" +
            "                        GROUP BY g.TITLE,g1.TITLE, p.TITLE, p.ID, p.alias,p.OPENING_BALANCE,p.transaction_type\n" +
            "                        ORDER BY alias asc",nativeQuery = true)
    List<TrialBalance> getTrialBalance();
}