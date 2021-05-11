package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.projection.authserver.VoucherTypeList;

import java.util.List;

@Repository
public interface VoucherTypeRepository extends JpaRepository<VoucherType,Integer> {

    List<VoucherTypeList> findAllByStatus(Boolean status);

    @Query(value = "SELECT v FROM VoucherType v")
    Page<VoucherTypeList> findAllVoucherType(Pageable pageable);


}
