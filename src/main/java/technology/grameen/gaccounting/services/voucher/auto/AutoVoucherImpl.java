package technology.grameen.gaccounting.services.voucher.auto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import technology.grameen.gaccounting.accounting.entity.*;
import technology.grameen.gaccounting.accounting.repositories.AutoVoucherRepository;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.AutoVoucherMapDetail;
import technology.grameen.gaccounting.requests.AutoVoucherRequest;
import technology.grameen.gaccounting.services.voucher.VoucherService;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutoVoucherImpl implements AutoVoucherService {

    private AutoVoucherRepository autoVoucherRepository;
    private VoucherService voucherService;

    public AutoVoucherImpl(AutoVoucherRepository autoVoucherRepository) {
        this.autoVoucherRepository = autoVoucherRepository;
    }

    @Override
    public AutoVoucherMap addVoucherMap(AutoVoucherMap voucherMap) throws CustomException {

        if(voucherMap.getName().isEmpty()){
            throw new CustomException("Name is required");
        }

        if(voucherMap.getAlias().isEmpty()){
            throw new CustomException("Alias is required");
        }

        return this.autoVoucherRepository.save(voucherMap);
    }

    @Override
    public Optional<AutoVoucherMapDetail> getByAlias(String module,String alias) {
        return autoVoucherRepository.findByModuleNameAndAlias(module,alias);
    }

    @Override
    public void setVoucherService(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @Override
    public Voucher saveAutoVoucher(AutoVoucherRequest request) throws CustomException {

        String alias = request.getAlias();
        String module = request.getModule();

        Optional<AutoVoucherMapDetail> autoVoucherMap = this.getByAlias(module,alias);

        if(!autoVoucherMap.isPresent()){
            throw new CustomException("Mapping not found for alias ["+alias+"]");
        }

        AutoVoucherMapDetail map = autoVoucherMap.get();
        Voucher v1 = voucherService.addVoucher(getVoucher(request,map));
        v1.getTransactions().stream().map(t->{
            t.setVoucher(null);
            return t;
        }).collect(Collectors.toSet());
        return v1;
    }

    private Voucher getVoucher(AutoVoucherRequest req, AutoVoucherMapDetail detail){
        Voucher voucher = new Voucher();
        voucher.setVoucherNo(req.getVoucherNo());
        voucher.setVoucherDate(req.getVoucherDate());
        voucher.setVoucherType(getVoucherType(detail));
        voucher.setNote(req.getNote());
        voucher.setOfficeId(req.getOfficeId());
        voucher.setStatus(true);
        voucher.setTotalCreditAmount(req.getTotalAmount());
        voucher.setTotalDebitAmount(req.getTotalAmount());
        voucher.addTransaction(getDebitTransaction(voucher,req,detail));
        voucher.addTransaction(getCreditTransaction(voucher,req,detail));
        return voucher;
    }

    private Transaction getDebitTransaction(Voucher voucher,AutoVoucherRequest req, AutoVoucherMapDetail detail){
        Transaction debit = new Transaction();
        debit.setVoucher(voucher);
        debit.setAmount(req.getTotalAmount());
        ChartAccount drAcc = new ChartAccount();
        drAcc.setId(detail.getDrHeader().getId());
        drAcc.setTitle(detail.getDrHeader().getTitle());
        debit.setChartAccount(drAcc);
        debit.setOfficeId(req.getOfficeId());
        debit.setTransactionDate(req.getVoucherDate());
        debit.setTransactionType("dr");
        return debit;
    }

    private Transaction getCreditTransaction(Voucher voucher,AutoVoucherRequest req, AutoVoucherMapDetail detail){
        Transaction credit = new Transaction();
        credit.setVoucher(voucher);
        credit.setAmount(req.getTotalAmount());
        ChartAccount crAcc = new ChartAccount();
        crAcc.setId(detail.getCrHeader().getId());
        crAcc.setTitle(detail.getCrHeader().getTitle());
        credit.setChartAccount(crAcc);
        credit.setOfficeId(req.getOfficeId());
        credit.setTransactionDate(req.getVoucherDate());
        credit.setTransactionType("cr");

        return credit;
    }

    private VoucherType getVoucherType(AutoVoucherMapDetail detail){
        VoucherType voucherType = new VoucherType();
        voucherType.setId(detail.getVoucherType().getId());
        voucherType.setName(detail.getVoucherType().getName());
        return voucherType;
    }

    @Override
    public Page<AutoVoucherMapDetail> getMappings(Pageable pageable) {
        return autoVoucherRepository.findAllMappings(pageable);
    }
}
