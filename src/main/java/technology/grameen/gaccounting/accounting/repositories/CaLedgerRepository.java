package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.ChartAccountLedger;
import technology.grameen.gaccounting.projection.LedgerBalance;
import technology.grameen.gaccounting.projection.OpeningBalanceDiff;

import java.util.Optional;

@Repository
public interface CaLedgerRepository extends JpaRepository<ChartAccountLedger,Long> {

    @Query(value = "SELECT p.alias, p.id,p.TITLE,NVL(p.OPENING_BALANCE,0) as openingBalance, " +
            "CASE " +
            "WHEN p.alias = 'asset' THEN " +
            "(NVL(p.OPENING_BALANCE,0)+(SUM(DEBIT)-SUM(CREDIT))) " +
            "WHEN p.alias = 'drawings' THEN " +
            "(NVL(p.OPENING_BALANCE,0)+(SUM(DEBIT)-SUM(CREDIT) )) " +
            "WHEN p.alias = 'expense' THEN " +
            "(NVL(p.OPENING_BALANCE,0)+ (SUM(DEBIT)-SUM(CREDIT))) " +
            "WHEN p.alias = 'income' THEN " +
            "(NVL(p.OPENING_BALANCE,0)+(SUM(CREDIT)-SUM(DEBIT) )) " +
            "WHEN p.alias = 'capital' THEN " +
            "(NVL(p.OPENING_BALANCE,0)+(SUM(CREDIT)-SUM(DEBIT) )) " +
            "WHEN p.alias = 'liabilities' THEN " +
            "(NVL(p.OPENING_BALANCE,0)+(SUM(CREDIT)-SUM(DEBIT) )) " +
            "END AS BALANCE, " +
            " SUM(DEBIT) AS DEBIT, SUM(CREDIT) AS credit FROM (SELECT ca.ID,cal.OPENING_BALANCE, cat.ALIAS, TITLE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, " +
            "  NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit " +
            "  FROM CA_LEDGERS cal " +
            " LEFT JOIN TRANSACTIONS t ON t.CHART_ACCOUNT_ID = cal.CHART_ACCOUNT_ID " +
            " JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID  " +
            " JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID " +
            "  WHERE ca.id = :caID " +
            " GROUP BY ca.ID, TITLE, TRANSACTION_TYPE, cat.ALIAS,cal.OPENING_BALANCE) p " +
            " GROUP BY p.TITLE, p.ID, p.alias,p.OPENING_BALANCE",nativeQuery = true)
    LedgerBalance getLedgerBalance(@Param("caID") Long ledgerAccountId);


    @Query(value = "SELECT SUM(NVL(OPENING_BALANCE,0)) as totalOpeningDr, " +
            " SUM(NVL(OPENING_CREDIT_BALANCE,0)) AS totalOpeningCr, " +
            " ABS(SUM(NVL(OPENING_BALANCE,0))-SUM(NVL(OPENING_CREDIT_BALANCE,0))) AS differenceAmount, " +
            " CASE WHEN (SUM(NVL(OPENING_BALANCE,0))> SUM(NVL(OPENING_CREDIT_BALANCE,0))) " +
            " THEN 'Dr' ELSE 'Cr' END AS balanceType " +
            " FROM CA_LEDGERS cal " +
            " JOIN CHART_ACCOUNTS ca ON cal.CHART_ACCOUNT_ID = ca.ID " +
            " JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID",nativeQuery = true)
    Optional<OpeningBalanceDiff> getTotalOpeningBalanceDifference();



}
