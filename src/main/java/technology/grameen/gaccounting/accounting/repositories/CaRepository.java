package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.projection.authserver.ChartAccountList;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaRepository extends JpaRepository<ChartAccount,Long> {

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<ChartAccountList> findAllChartAccounts();

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            " WHERE cas.is_ledger=1 " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<ChartAccountList> findAllLedgerAccounts();

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            " WHERE cas.is_ledger=0 " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<ChartAccountList> findAllGroupAccounts();

    Optional<ChartAccount> findByCode(String code);

}
