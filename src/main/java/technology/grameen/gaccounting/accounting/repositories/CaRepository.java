package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.projection.authserver.ChartAccountList;
import technology.grameen.gaccounting.projection.authserver.GroupDetail;
import technology.grameen.gaccounting.projection.authserver.LedgerAccountList;
import technology.grameen.gaccounting.projection.authserver.LedgerDetail;

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
            "parentCas.title as parent, parentCas.id as parentId, cas.is_ledger as IsLedger,cal.contact_address as contactAddress,cal.contact_name as contactName, " +
            "cal.contact_email as contactEmail,cal.contact_phone as contactPhone FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CA_LEDGERS cal ON cal.CHART_ACCOUNT_ID = cas.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=1 " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<LedgerAccountList> findAllLedgerAccounts();

    @Query(value = "SELECT cas.id, cat.id as caTypeId, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=0 " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<ChartAccountList> findAllGroupAccounts();

    Optional<ChartAccount> findByCode(String code);

    @Query(value = "SELECT ca FROM ChartAccount ca " +
            "LEFT JOIN FETCH ca.parent pa " +
            "LEFT JOIN FETCH ca.chartAccountGroup cag " +
            "LEFT JOIN FETCH ca.chartAccountLedger cal " +
            "JOIN FETCH ca.chartAccountType cat WHERE ca.id=:id")
    Optional<GroupDetail> findGroupById(@Param("id") Long id);

    @Query(value = "SELECT ca FROM ChartAccount ca " +
            "LEFT JOIN FETCH ca.parent pa " +
            "LEFT JOIN FETCH ca.chartAccountGroup cag " +
            "LEFT JOIN FETCH ca.chartAccountLedger cal " +
            "JOIN FETCH ca.chartAccountType cat WHERE ca.id=:id")
    Optional<LedgerDetail> findLedgerById(@Param("id") Long id);

}
