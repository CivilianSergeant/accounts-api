package technology.grameen.gaccounting.services.voucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.projection.authserver.VoucherTypeList;

import java.util.List;

public interface VoucherTypeService {

    List<VoucherTypeList> getVoucherTypeList();
    Page<VoucherTypeList> getVoucherTypeList(Pageable pageable);

    VoucherType addVoucherType(VoucherType voucherType);

}
