package technology.grameen.gaccounting.services.report;

import org.springframework.stereotype.Service;
import technology.grameen.gaccounting.accounting.repositories.ReportRepository;
import technology.grameen.gaccounting.projection.TrialBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceIml implements ReportService {

    private ReportRepository reportRepository;
    CaType caType;
    PrimaryGroup primaryGroup;
    SubGroup subGroup;
    LedgerAccount ledgerAccount;

    ReportServiceIml(ReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    @Override
    public List<CaType> getTrialBalance() {
        List<TrialBalance> trialBalances =  reportRepository.getTrialBalance();
        List<CaType> caTypes = new ArrayList<>();
        List<String> root = new ArrayList<>();


        trialBalances.forEach(t->{

            if(!root.contains(t.getAlias())) {
                root.add(t.getAlias());
                caType = new CaType(t.getAlias());
                caTypes.add(caType);
                if(!root.contains(t.getPrimaryGroup())){
                    root.add(t.getPrimaryGroup());
                    primaryGroup = new PrimaryGroup(t.getPrimaryGroup());
                    caType.getPrimaryGroups().add(primaryGroup);

                    if(!root.contains(t.getSubGroup())){
                        root.add(t.getSubGroup());
                        subGroup = new SubGroup(t.getSubGroup());
                        primaryGroup.getSubGroups().add(subGroup);
                    }
                    if(!root.contains(t.getLedgerAcc())){
                        root.add(t.getLedgerAcc());
                        ledgerAccount = new LedgerAccount(t.getId(),t.getLedgerAcc(),
                                t.getTransType(), t.getOpeningBalance(),t.getDebit(),t.getCredit());
                        subGroup.setDebit(subGroup.getDebit().add(t.getDebit()));
                        subGroup.setCredit(subGroup.getCredit().add(t.getCredit()));
                        subGroup.getLedgerAccounts().add(ledgerAccount);
                    }
                }
            }else{
                if(!root.contains(t.getPrimaryGroup())){
                    root.add(t.getPrimaryGroup());
                    primaryGroup = new PrimaryGroup(t.getPrimaryGroup());
                    caType.getPrimaryGroups().add(primaryGroup);
                }else{
                    if(!root.contains(t.getSubGroup())){
                        root.add(t.getSubGroup());
                        subGroup = new SubGroup(t.getSubGroup());
                        primaryGroup.getSubGroups().add(subGroup);
                    }else{

                        if(!root.contains(t.getLedgerAcc())){
                            root.add(t.getLedgerAcc());
                            ledgerAccount = new LedgerAccount(t.getId(),t.getLedgerAcc(),
                                    t.getTransType(), t.getOpeningBalance(),t.getDebit(),t.getCredit());
                            subGroup.setDebit(subGroup.getDebit().add(t.getDebit()));
                            subGroup.setCredit(subGroup.getCredit().add(t.getCredit()));
                            subGroup.getLedgerAccounts().add(ledgerAccount);
                        }else {

                            ledgerAccount.setDebit(ledgerAccount.getDebit().add(t.getDebit()));
                            ledgerAccount.setCredit(ledgerAccount.getCredit().add(t.getCredit()));
//                            ledgerAccount = new LedgerAccount(t.getId(), t.getLedgerAcc(),
//                                    t.getTransType(), t.getOpeningBalance(), t.getDebit(), t.getCredit());
                            subGroup.setDebit(subGroup.getDebit().add(t.getDebit()));
                            subGroup.setCredit(subGroup.getCredit().add(t.getCredit()));
//                            subGroup.getLedgerAccounts().add(ledgerAccount);
                        }
                    }
                }

            }
        });

        return caTypes;
    }
}
