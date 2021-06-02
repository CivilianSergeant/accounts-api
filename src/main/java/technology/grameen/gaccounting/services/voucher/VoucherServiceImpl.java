package technology.grameen.gaccounting.services.voucher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.accounting.repositories.TransactionRepository;
import technology.grameen.gaccounting.accounting.repositories.VoucherRepository;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.VoucherDetail;
import technology.grameen.gaccounting.projection.VoucherList;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements  VoucherService{

    VoucherRepository voucherRepository;
    TransactionRepository transactionRepository;

    VoucherServiceImpl(VoucherRepository voucherRepository,
                       TransactionRepository transactionRepository){
        this.voucherRepository = voucherRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<VoucherList> getVouchers(Pageable pageable) {
        return voucherRepository.findAllVouchers(pageable);
    }

    @Override
    @Transactional
    public Voucher addVoucher(Voucher voucher) throws CustomException{

        if(voucher==null){
            throw new CustomException("Sorry! Voucher should not be null");
        }

        if(voucher.getTransactions()==null || voucher.getTransactions().size()==0){
            throw new CustomException("Sorry! Voucher cannot save without any transaction.");
        }

        if(voucher.getVoucherType() == null){
            throw new CustomException("Sorry! Voucher Type should not be empty");
        }

        Voucher voucherSaved = voucherRepository.save(voucher);

        if(voucherSaved != null && voucherSaved.getId() > 0){
            voucherSaved.getTransactions().stream().map(t->{
                t.setVoucher(voucherSaved);
                return t;
            }).collect(Collectors.toSet());

            transactionRepository.saveAll(voucherSaved.getTransactions());

        }
        return voucherSaved;
    }

    @Override
    public Optional<VoucherDetail> getVoucherDetail(Long id) {
        return voucherRepository.findVoucherById(id);
    }
}
