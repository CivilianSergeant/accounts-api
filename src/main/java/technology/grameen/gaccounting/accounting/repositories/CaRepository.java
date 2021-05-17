package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.projection.authserver.ChartAccountList;

import java.util.List;

@Repository
public interface CaRepository extends JpaRepository<ChartAccount,Long> {

    @Query(value = "SELECT cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<ChartAccountList> findAllChartAccounts();
}
