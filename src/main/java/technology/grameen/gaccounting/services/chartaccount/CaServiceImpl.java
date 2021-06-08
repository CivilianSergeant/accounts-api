package technology.grameen.gaccounting.services.chartaccount;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.accounting.repositories.CaRepository;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CaServiceImpl implements CaService{

    CaRepository caRepository;
    CaLedgerService caLedgerService;
    CaGroupService caGroupService;

    CaServiceImpl(CaRepository caRepository,
                  CaLedgerService caLedgerService,
                  CaGroupService caGroupService){

        this.caRepository = caRepository;
        this.caLedgerService = caLedgerService;
        this.caGroupService = caGroupService;
    }

    @Override
    public List<ChartAccountList> getChartAccounts() {
        return caRepository.findAllChartAccounts();
    }

    @Override
    public List<LedgerAccountList> getLedgerAccounts() {
        return caRepository.findAllLedgerAccounts();
    }

    @Override
    public List<ChartAccountList> getGroupAccounts() {
        return caRepository.findAllGroupAccounts();
    }

    @Override
    @Transactional
    public ChartAccount addChartAccount(ChartAccount chartAccount) throws CustomException {

        Optional<ChartAccount> ca = caRepository.findByCode(chartAccount.getCode());
        if(ca!=null && ca.isPresent() && chartAccount.getId()==null){
            throw new CustomException("Code already exist");
        }

        if(chartAccount.getChartAccountType()==null ||
            chartAccount.getChartAccountType().getId() == null){
            throw new CustomException("Chart Account Type Required");
        }

        ChartAccount account = null;

            account = caRepository.save(chartAccount);

            if(account.getId()>0 && account.getLedger()){
                chartAccount.getChartAccountLedger().setChartAccount(account);
                caLedgerService.addCaLedger(chartAccount.getChartAccountLedger());

            }else if(account.getId()>0 && !account.getLedger() && chartAccount.getId()==null){
                account.setChartAccountLedger(null);

                caGroupService.addCaGroup(account);
            }

        return account;
    }


    @Override
    public Optional<GroupDetail> getGroupDetail(Long id) {
        return caRepository.findGroupById(id);
    }

    @Override
    public Optional<LedgerDetail> getLedgerDetail(Long id) {
        return caRepository.findLedgerById(id);
    }

    @Override
    public LedgerBalance getLedgerBalance(Long id) {
        return caLedgerService.getBalance(id);
    }

    @Override
    public Optional<OpeningBalanceDiff> getOpeningBalanceDiff() {
        return caLedgerService.getOpeningBalanceDifference();
    }
}
