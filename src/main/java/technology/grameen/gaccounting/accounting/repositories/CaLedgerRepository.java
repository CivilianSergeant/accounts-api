package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.ChartAccountLedger;
import technology.grameen.gaccounting.projection.LedgerBalance;

@Repository
public interface CaLedgerRepository extends JpaRepository<ChartAccountLedger,Long> {

    @Query(value = "SELECT p.alias, p.id,p.TITLE, " +
            "CASE " +
            "WHEN p.alias = 'asset' THEN " +
            "SUM(DEBIT)-SUM(CREDIT) " +
            "WHEN p.alias = 'drawings' THEN  " +
            "SUM(DEBIT)-SUM(CREDIT) " +
            "WHEN p.alias = 'expense' THEN  " +
            "SUM(DEBIT)-SUM(CREDIT) " +
            "WHEN p.alias = 'income' THEN  " +
            "SUM(CREDIT)-SUM(DEBIT) " +
            "WHEN p.alias = 'capital' THEN  " +
            "SUM(CREDIT)-SUM(DEBIT) " +
            "WHEN p.alias = 'liabilities' THEN  " +
            "SUM(CREDIT)-SUM(DEBIT) " +
            "END AS BALANCE, " +
            "SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM (SELECT ca.ID, cat.ALIAS, TITLE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, " +
            "            NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit FROM TRANSACTIONS t " +
            "            JOIN CHART_ACCOUNTS ca ON t.CHART_ACCOUNT_ID = ca.ID  " +
            "            JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID  " +
            "            WHERE ca.id = :caID " +
            "            GROUP BY ca.ID, TITLE, TRANSACTION_TYPE, cat.ALIAS) p " +
            "            GROUP BY p.TITLE, p.ID, p.alias",nativeQuery = true)
    LedgerBalance getLedgerBalance(@Param("caID") Long ledgerAccountId);
}
