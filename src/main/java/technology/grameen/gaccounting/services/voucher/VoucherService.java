package technology.grameen.gaccounting.services.voucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.VoucherList;

public interface VoucherService {


    Page<VoucherList> getVouchers(Pageable pageable);

    Voucher addVoucher(Voucher voucher) throws CustomException;
}
