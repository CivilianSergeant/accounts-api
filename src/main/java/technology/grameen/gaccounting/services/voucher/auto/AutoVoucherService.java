package technology.grameen.gaccounting.services.voucher.auto;

import technology.grameen.gaccounting.accounting.entity.AutoVoucherMap;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.exceptions.CustomException;

public interface AutoVoucherService {

    AutoVoucherMap addVoucher(AutoVoucherMap voucherMap) throws CustomException;
}
