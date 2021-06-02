package technology.grameen.gaccounting.services.voucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.VoucherDetail;
import technology.grameen.gaccounting.projection.VoucherList;

import java.util.Optional;

public interface VoucherService {


    Page<VoucherList> getVouchers(Pageable pageable);

    Voucher addVoucher(Voucher voucher) throws CustomException;

    Optional<VoucherDetail> getVoucherDetail(Long id);
}
