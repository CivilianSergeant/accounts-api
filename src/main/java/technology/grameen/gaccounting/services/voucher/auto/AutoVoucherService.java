package technology.grameen.gaccounting.services.voucher.auto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import technology.grameen.gaccounting.accounting.entity.AutoVoucherMap;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.AutoVoucherMapDetail;
import technology.grameen.gaccounting.requests.AutoVoucherRequest;
import technology.grameen.gaccounting.services.voucher.VoucherService;

import java.util.List;
import java.util.Optional;

public interface AutoVoucherService {

    AutoVoucherMap addVoucherMap(AutoVoucherMap voucherMap) throws CustomException;

    Optional<AutoVoucherMapDetail> getByAlias(String module,String alias);
    List<AutoVoucherMapDetail> getByModule(String module);

    Voucher saveAutoVoucher(AutoVoucherRequest request) throws CustomException;

    Page<AutoVoucherMapDetail> getMappings(Pageable pageable);

    void setVoucherService(VoucherService voucherService);
}
