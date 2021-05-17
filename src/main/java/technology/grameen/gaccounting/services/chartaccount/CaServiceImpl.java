package technology.grameen.gaccounting.services.chartaccount;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.accounting.repositories.CaRepository;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.authserver.ChartAccountList;

import java.util.List;

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
    @Transactional
    public ChartAccount addChartAccount(ChartAccount chartAccount) throws CustomException {

        if(chartAccount.getChartAccountType()==null ||
            chartAccount.getChartAccountType().getId() == null){
            throw new CustomException("Chart Account Type Required");
        }
        ChartAccount account = null;

            account = caRepository.save(chartAccount);

            if(account.getId()>0 && account.getLedger()){
                chartAccount.getChartAccountLedger().setChartAccount(account);
                caLedgerService.addCaLedger(chartAccount.getChartAccountLedger());

            }else if(account.getId()>0 && !account.getLedger()){
                account.setChartAccountLedger(null);

                caGroupService.addCaGroup(account);
            }

        return account;
    }

}
