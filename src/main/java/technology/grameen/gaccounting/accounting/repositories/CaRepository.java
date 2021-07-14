package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.projection.ChartAccountList;
import technology.grameen.gaccounting.projection.GroupDetail;
import technology.grameen.gaccounting.projection.LedgerAccountList;
import technology.grameen.gaccounting.projection.LedgerDetail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CaRepository extends JpaRepository<ChartAccount,Long> {

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId,parentCas.code as parentCode, cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<ChartAccountList> findAllChartAccounts();

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, parentCas.code as parentCode, cas.is_ledger as IsLedger,cal.contact_address as contactAddress,cal.contact_name as contactName, " +
            "cal.contact_email as contactEmail,cal.contact_phone as contactPhone FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CA_LEDGERS cal ON cal.CHART_ACCOUNT_ID = cas.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=1 " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<LedgerAccountList> findAllLedgerAccounts();


    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, parentCas.code as parentCode, cas.is_ledger as IsLedger,cal.contact_address as contactAddress,cal.contact_name as contactName, " +
            "cal.contact_email as contactEmail,cal.contact_phone as contactPhone FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CA_LEDGERS cal ON cal.CHART_ACCOUNT_ID = cas.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=1 ",
            countQuery ="select count(*) FROM CA_TYPES cat " +
                    " JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id "+
                    " LEFT JOIN CA_LEDGERS cal ON cal.CHART_ACCOUNT_ID = cas.id "+
                    " LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
                    " WHERE cas.is_ledger=1"
                    , nativeQuery = true)
    Page<LedgerAccountList> findAllLedgerAccounts(Pageable pageable);

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, parentCas.code  as parentCode, cas.is_ledger as IsLedger,cal.contact_address as contactAddress,cal.contact_name as contactName, " +
            "cal.contact_email as contactEmail,cal.contact_phone as contactPhone FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CA_LEDGERS cal ON cal.CHART_ACCOUNT_ID = cas.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=1 " +
            " AND upper(cas.title) LIKE upper('%'||:title||'%') " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    Page<LedgerAccountList> findAllLedgerAccountsByTitle(@Param("title") String title, Pageable pageable);

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, parentCas.code  as parentCode, cas.is_ledger as IsLedger,cal.contact_address as contactAddress,cal.contact_name as contactName, " +
            "cal.contact_email as contactEmail,cal.contact_phone as contactPhone FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CA_LEDGERS cal ON cal.CHART_ACCOUNT_ID = cas.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=1 " +
            " AND upper(cat.name) LIKE upper('%'||:type||'%') " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    Page<LedgerAccountList> findAllLedgerAccountsByType(@Param("type") String title, Pageable pageable);

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, parentCas.code as parentCode, cas.is_ledger as IsLedger,cal.contact_address as contactAddress,cal.contact_name as contactName, " +
            "cal.contact_email as contactEmail,cal.contact_phone as contactPhone FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CA_LEDGERS cal ON cal.CHART_ACCOUNT_ID = cas.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=1 " +
            " AND upper(cas.code) LIKE upper('%'||:code||'%') " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    Page<LedgerAccountList> findAllLedgerAccountsByCode(@Param("code") String code, Pageable pageable);

    @Query(value = "SELECT cas.id, cas.ca_level as caLevel, cat.id as caTypeId, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.code as parentCode, parentCas.id as parentId, cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=0 " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<ChartAccountList> findAllGroupAccounts();


    @Query(value = "SELECT cas.id, cas.ca_level as caLevel, cat.id as caTypeId, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, parentCas.code as parentCode, cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=0 " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    Page<ChartAccountList> findAllGroups(Pageable pageable);

    @Query(value = "SELECT cas.id, cas.ca_level as caLevel, cat.id as caTypeId, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, parentCas.code as parentCode, cas.is_ledger as IsLedger FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            " LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=0 " +
            " AND upper(cas.title) like upper('%'||:title||'%')" +
            " ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<ChartAccountList> findAllGroupsByTitle(@Param("title") String title);


    Optional<ChartAccount> findByCode(String code);

    interface ChartAccountType{
        Integer getId();
        String getAlias();
    }
    interface ChartAccountLedger{
        BigDecimal getOpeningBalance();
        BigDecimal getOpeningCreditBalance();
    }
    interface LedgerInfo{
        Long getId();
        String getTitle();
        Boolean getLedger();
        ChartAccountType getChartAccountType();
        ChartAccountLedger getChartAccountLedger();
    }
    @Query(value = "SELECT ca FROM ChartAccount ca JOIN FETCH ca.chartAccountType cat " +
            " JOIN FETCH ca.chartAccountLedger cal " +
            "WHERE ca.code = :code")
    Optional<LedgerInfo> findLedgerByCode(@Param("code") String code);


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

    @Query(value = "SELECT cas.id, cat.name as typeName,cat.code as ctCode,cas.title,cas.code as caCode, " +
            "parentCas.title as parent, parentCas.id as parentId, parentCas.code as parentCode, cas.is_ledger as IsLedger,cal.contact_address as contactAddress,cal.contact_name as contactName, " +
            "cal.contact_email as contactEmail,cal.contact_phone as contactPhone FROM CA_TYPES cat " +
            "JOIN CHART_ACCOUNTS cas ON cas.CHART_ACCOUNT_TYPE_ID = cat.id " +
            "LEFT JOIN CA_LEDGERS cal ON cal.CHART_ACCOUNT_ID = cas.id " +
            "LEFT JOIN CHART_ACCOUNTS parentCas ON cas.PARENT_ID = parentCas.id " +
            " WHERE cas.is_ledger=1 AND (cas.code LIKE :keyword||'%' OR cas.title LIKE :keyword||'%') " +
            "ORDER BY cat.code ASC, cas.code ASC",nativeQuery = true)
    List<LedgerAccountList> findByCodeOrTitleContainingIgnoreCase(String keyword);
}
