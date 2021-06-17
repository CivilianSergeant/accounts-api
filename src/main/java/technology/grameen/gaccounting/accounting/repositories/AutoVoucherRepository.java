package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.AutoVoucherMap;
import technology.grameen.gaccounting.projection.AutoVoucherMapDetail;

import java.util.Optional;

@Repository
public interface AutoVoucherRepository extends JpaRepository<AutoVoucherMap,Long> {

    @Query(value = "SELECT av FROM AutoVoucherMap av JOIN FETCH av.crHeader crH " +
            " JOIN FETCH av.voucherType vt" +
            " JOIN FETCH av.drHeader drH" +
            " LEFT JOIN FETCH drH.chartAccountGroup drCag " +
            " LEFT JOIN FETCH drH.chartAccountLedger drCal " +
            " LEFT JOIN FETCH crH.chartAccountGroup cag " +
            " LEFT JOIN FETCH crH.chartAccountLedger cal " +
            " WHERE av.moduleName = :moduleName AND av.alias = :alias")
    Optional<AutoVoucherMapDetail> findByModuleNameAndAlias(@Param("moduleName") String module,
                                                            @Param("alias") String alias);

    @Query(value = "SELECT av FROM AutoVoucherMap av JOIN FETCH av.crHeader crH " +
            " JOIN FETCH av.voucherType vt" +
            " JOIN FETCH av.drHeader drH" +
            " LEFT JOIN FETCH drH.chartAccountGroup drCag " +
            " LEFT JOIN FETCH drH.chartAccountLedger drCal " +
            " LEFT JOIN FETCH crH.chartAccountGroup cag " +
            " LEFT JOIN FETCH crH.chartAccountLedger cal ",
    countQuery = "SELECT av FROM AutoVoucherMap av JOIN av.crHeader crH " +
            " JOIN av.voucherType vt" +
            " JOIN av.drHeader drH" +
            " LEFT JOIN drH.chartAccountGroup drCag " +
            " LEFT JOIN drH.chartAccountLedger drCal " +
            " LEFT JOIN crH.chartAccountGroup cag " +
            " LEFT JOIN crH.chartAccountLedger cal ")
    Page<AutoVoucherMapDetail> findAllMappings(Pageable pageable);
}
