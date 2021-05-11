package technology.grameen.gaccounting.services.voucher;

import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.projection.authserver.VoucherTypeList;

import java.util.List;

public interface VoucherTypeService {

    List<VoucherTypeList> getVoucherTypeList();

    VoucherType addVoucherType(VoucherType voucherType);

}
