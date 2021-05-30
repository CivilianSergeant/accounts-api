package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.projection.VoucherList;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {

    @Query(value = "SELECT v FROM Voucher v JOIN FETCH v.voucherType vt",
    countQuery = "SELECT v FROM Voucher v JOIN FETCH v.voucherType vt")
    Page<VoucherList> findAllVouchers(Pageable pageable);
}
