package technology.grameen.gaccounting.services.voucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.projection.VoucherTypeList;

import java.util.List;
import java.util.Optional;

public interface VoucherTypeService {

    List<VoucherTypeList> getVoucherTypeList();
    Page<VoucherTypeList> getVoucherTypeList(Pageable pageable);

    VoucherType addVoucherType(VoucherType voucherType);
    Optional<VoucherType> findByAlias(String alias);
}
