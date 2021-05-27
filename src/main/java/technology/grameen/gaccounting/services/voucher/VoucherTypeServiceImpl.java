package technology.grameen.gaccounting.services.voucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.accounting.repositories.VoucherTypeRepository;
import technology.grameen.gaccounting.projection.authserver.VoucherTypeList;

import java.util.List;
import java.util.Optional;

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
    public Page<VoucherTypeList> getVoucherTypeList(Pageable pageable) {
        return voucherTypeRepository.findAllVoucherType(pageable);
    }

    @Override
    @Transactional
    public VoucherType addVoucherType(VoucherType voucherType) {
        return voucherTypeRepository.save(voucherType);
    }

    @Override
    public Optional<VoucherType> findByAlias(String alias) {
        return voucherTypeRepository.findByAlias(alias);
    }
}
