package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.projection.VoucherDetail;
import technology.grameen.gaccounting.projection.VoucherList;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {

    @Query(value = "SELECT v FROM Voucher v JOIN FETCH v.voucherType vt",
    countQuery = "SELECT count(v) FROM Voucher v JOIN v.voucherType vt")
    Page<VoucherList> findAllVouchers(Pageable pageable);

    @Query(value = "SELECT v FROM Voucher v JOIN FETCH v.transactions t WHERE v.id =:id")
    Optional<VoucherDetail> findVoucherById(@Param("id") Long id);

    @Query(value = "SELECT v FROM Voucher v JOIN FETCH v.voucherType vt WHERE v.voucherNo =:voucherNo")
    List<VoucherDetail> findByVoucherNo(@Param("voucherNo") String number);

    @Query(value = "SELECT v FROM Voucher v JOIN FETCH v.voucherType vt WHERE v.voucherNo =:voucherNo" +
            " AND v.id <> :vid")
    List<VoucherDetail> findByVoucherNoIsNotSameVid(@Param("voucherNo") String number, @Param("vid") Long vid);
}
