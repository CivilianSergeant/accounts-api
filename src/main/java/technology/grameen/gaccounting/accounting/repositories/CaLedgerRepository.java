package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.ChartAccountLedger;
import technology.grameen.gaccounting.projection.LedgerBalance;

@Repository
public interface CaLedgerRepository extends JpaRepository<ChartAccountLedger,Long> {

    @Query(value = "SELECT ca.ID, TITLE, NVL(CASE WHEN TRANSACTION_TYPE='dr' THEN SUM(NVL(AMOUNT,0)) END,0) AS debit, " +
            "NVL(CASE WHEN TRANSACTION_TYPE = 'cr' THEN SUM(NVL(AMOUNT,0)) END,0) AS credit FROM TRANSACTIONS t " +
            "JOIN CHART_ACCOUNTS ca ON t.CHART_ACCOUNT_ID = ca.ID " +
            "JOIN CA_TYPES cat ON cat.ID = ca.CHART_ACCOUNT_TYPE_ID " +
            "WHERE ca.id = :caID " +
            "GROUP BY ca.ID, TRANSACTION_TYPE,TITLE",nativeQuery = true)
    LedgerBalance getLedgerBalance(@Param("caID") Long ledgerAccountId);
}
