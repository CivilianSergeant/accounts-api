package technology.grameen.gaccounting.services.voucher.auto;

import technology.grameen.gaccounting.accounting.entity.AutoVoucherMap;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.accounting.repositories.AutoVoucherRepository;
import technology.grameen.gaccounting.exceptions.CustomException;

public class AutoVoucherImpl implements AutoVoucherService {

    private AutoVoucherRepository autoVoucherRepository;

    public AutoVoucherImpl(AutoVoucherRepository autoVoucherRepository) {
        this.autoVoucherRepository = autoVoucherRepository;
    }

    @Override
    public AutoVoucherMap addVoucher(AutoVoucherMap voucherMap) throws CustomException {
        return this.autoVoucherRepository.save(voucherMap);
    }
}
