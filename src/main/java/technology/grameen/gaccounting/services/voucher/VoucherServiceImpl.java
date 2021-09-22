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

import java.util.List;
import java.util.Optional;

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
            voucher.getTransactions().forEach(t->{
                t.setVoucher(voucherSaved);
                t.setTransactionDate(voucherSaved.getVoucherDate());
                transactionRepository.save(t);

            });

//            transactionRepository.saveAll(voucherSaved.getTransactions());

        }
        return voucherSaved;
    }

    @Override
    public Optional<VoucherDetail> getVoucherDetail(Long id) {
        return voucherRepository.findVoucherById(id);
    }

    @Override
    public List<VoucherDetail> isVoucherNoUnique(String number) {
        return voucherRepository.findByVoucherNo(number);
    }

    @Override
    public List<VoucherDetail> isVoucherNoUnique(String number, Long voucherId) {
        return voucherRepository.findByVoucherNoIsNotSameVid(number,voucherId);
    }

    @Override
    public Boolean deleteVoucher(Long id) {
        Optional<Voucher> voucherOptional = voucherRepository.findById(id);
        if(voucherOptional.isPresent()){
            voucherRepository.delete(voucherOptional.get());
            return true;
        }
        return false;
    }
}
