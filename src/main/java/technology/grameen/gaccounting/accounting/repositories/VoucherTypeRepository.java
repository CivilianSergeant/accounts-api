package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.projection.VoucherTypeList;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherTypeRepository extends JpaRepository<VoucherType,Integer> {

    List<VoucherTypeList> findAllByStatus(Boolean status);

    @Query(value = "SELECT v FROM VoucherType v Order By v.numberPrefix ASC")
    Page<VoucherTypeList> findAllVoucherType(Pageable pageable);

    @Query(value = "SELECT v FROM VoucherType v WHERE v.alias=:alias")
    Optional<VoucherType> findByAlias(@Param("alias") String alias);


}
