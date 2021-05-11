package technology.grameen.gaccounting.services.voucher;

import org.springframework.stereotype.Service;
import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.accounting.repositories.VoucherTypeRepository;
import technology.grameen.gaccounting.projection.authserver.VoucherTypeList;

import java.util.List;

@Service
public class VoucherTypeServiceImpl implements VoucherTypeService{

    VoucherTypeRepository voucherTypeRepository;

    VoucherTypeServiceImpl(VoucherTypeRepository voucherTypeRepository){
        this.voucherTypeRepository = voucherTypeRepository;
    }

    @Override
    public List<VoucherTypeList> getVoucherTypeList() {
        return voucherTypeRepository.findAllByStatus(true);
    }

    @Override
    public VoucherType addVoucherType(VoucherType voucherType) {
        VoucherType voucherType1 = voucherTypeRepository.save(voucherType);
        voucherType1.setAutoVoucherMaps(null);
        voucherType1.setAccMaps(null);
        voucherType1.setVouchers(null);
        return voucherType1;
    }
}
