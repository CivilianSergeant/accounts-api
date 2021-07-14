package technology.grameen.gaccounting.services.report;

import org.springframework.stereotype.Service;
import technology.grameen.gaccounting.accounting.entity.GeneralSetting;
import technology.grameen.gaccounting.accounting.repositories.CaRepository;
import technology.grameen.gaccounting.accounting.repositories.GeneralSettingRepository;
import technology.grameen.gaccounting.accounting.repositories.ReportRepository;
import technology.grameen.gaccounting.projection.ReportData;
import technology.grameen.gaccounting.services.UtilService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReportServiceIml implements ReportService {

    private ReportRepository reportRepository;
    private CaRepository caRepository;

    private CaType caType;
    private PrimaryGroup primaryGroup;
    private SubGroup subGroup;
    private LedgerAccount ledgerAccount;
    private List<String> root;
    List<CaType> caTypes;
    private UtilService utilService;
    Double totalCreditAmount = 0.0;
    Double totalDebitAmount = 0.0;

    ReportServiceIml(ReportRepository reportRepository,
                     UtilService utilService,CaRepository caRepository){
        this.reportRepository = reportRepository;
        this.utilService = utilService;
        this.caRepository = caRepository;

    }

    @Override
    public List<CaType> getTrialBalance() {


        caTypes = new ArrayList<>();

            utilService.loadData();
            String yearStart = utilService.getFinancialStartDate();
            String yearEnd = utilService.getFinancialEndDate();

            List<ReportData> reportData =  reportRepository.getTrialBalance(yearStart,yearEnd);

            root = new ArrayList<>();


            reportData.forEach((ReportData t)->{
                this.processData(root,t,yearStart,yearEnd);
            });


        return caTypes;
    }

    @Override
    public Map<String,Object> getIncomeStatement() {
        utilService.loadData();
        totalDebitAmount = 0.0;
        totalCreditAmount = 0.0;
        String yearStart = utilService.getFinancialStartDate();
        String yearEnd = utilService.getFinancialEndDate();
        caTypes = new ArrayList<>();
        root = new ArrayList<>();


        List<ReportData> reportData = reportRepository.getLedgerWiseTransactions("income",yearStart,yearEnd);
        reportData.forEach((ReportData t)->{
            this.processData(root,t,yearStart,yearEnd);
        });

        Optional<CaType> incomeType = caTypes.stream()
                                .filter(ct->ct.type.equalsIgnoreCase("income"))
                                .findFirst();
        if(incomeType.isPresent()) {
            incomeType.get().getPrimaryGroups().stream().forEach((PrimaryGroup pg) -> {
                totalCreditAmount += pg.getCreditAmount().doubleValue();
                totalDebitAmount += pg.getDebitAmount().doubleValue();
            });
        }


        reportData = reportRepository.getLedgerWiseTransactions("expense", yearStart, yearEnd);
        reportData.forEach((ReportData t)->{
            this.processData(root,t,yearStart,yearEnd);
        });

        Optional<CaType> expenseType = caTypes.stream()
                                        .filter(ct->ct.type.equalsIgnoreCase("expense"))
                                        .findFirst();

        if(expenseType.isPresent()) {
            expenseType.get().getPrimaryGroups().stream().forEach((PrimaryGroup pg) -> {
                totalCreditAmount += pg.getCreditAmount().doubleValue();
                totalDebitAmount += pg.getDebitAmount().doubleValue();
            });
        }

        Map<String, Object> result = new HashMap<>();
        result.put("records",caTypes);
        result.put("netProfit",(totalCreditAmount-totalDebitAmount));
        return result;
    }


    @Override
    public Map<String, Object> getBalanceSheet() {
        utilService.loadData();
        String yearStart = utilService.getFinancialStartDate();
        String yearEnd = utilService.getFinancialEndDate();
        caTypes = new ArrayList<>();
        root = new ArrayList<>();
        List<String> assetTypes = new ArrayList<>();
        assetTypes.add("asset");
        List<ReportData> assetData = reportRepository.getLedgerWiseTransactionsByTypes(assetTypes,yearStart,yearEnd);
        Map<String, Object> result = new HashMap<>();
        assetData.stream().forEach((ReportData r)->{
            this.processData(root,r,yearStart,yearEnd);
        });

        result.put("asset",caTypes);
        caTypes = new ArrayList<>();

        List<String> liabilitiesTypes = new ArrayList<>();
        liabilitiesTypes.add("liabilities");
        liabilitiesTypes.add("capital");
        liabilitiesTypes.add("drawings");

        List<ReportData> liabilitiesData = reportRepository.getLedgerWiseTransactionsByTypes(liabilitiesTypes,yearStart,yearEnd);
        liabilitiesData.stream().forEach((ReportData r)->{
            this.processData(root,r,yearStart,yearEnd);
        });
        result.put("liabilities",caTypes);

        Object netProfit = (this.getIncomeStatement()).get("netProfit");

        result.put("netProfit",netProfit);

        return result;
    }

    private void  processData (List<String> root, ReportData t, String yearStart, String yearEnd){
        if(!root.contains(t.getAlias())) {
            root.add(t.getAlias());
            caType = new CaType(t.getAlias());
            caType.setFinYearStart(yearStart);
            caType.setFinYearEnd(yearEnd);

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
                this.addLedgerAccount(t,true);
            }
        }else{
            if(!root.contains(t.getPrimaryGroup())){
                root.add(t.getPrimaryGroup());
                primaryGroup = new PrimaryGroup(t.getPrimaryGroup());
                caType.getPrimaryGroups().add(primaryGroup);
                if(!root.contains(t.getSubGroup())){
                    root.add(t.getSubGroup());
                    subGroup = new SubGroup(t.getSubGroup());
                    primaryGroup.getSubGroups().add(subGroup);
                    this.addLedgerAccount(t,true);
                }else{
                    this.addLedgerAccount(t,true);
                }
            }else{
                if(!root.contains(t.getSubGroup())){
                    root.add(t.getSubGroup());
                    subGroup = new SubGroup(t.getSubGroup());
                    primaryGroup.getSubGroups().add(subGroup);
                    this.addLedgerAccount(t,true);
                }else{

                   this.addLedgerAccount(t,true);
                }
            }

        }
    }

    private void addLedgerAccount(ReportData t, Boolean isNew ){
        if(!root.contains(t.getLedgerAcc())) {
            root.add(t.getLedgerAcc());
            ledgerAccount = new LedgerAccount(t.getId(), t.getLedgerAcc(),
                    t.getTransType(), t.getOpeningBalance(), t.getOpeningCreditBalance(), t.getDebit(), t.getCredit());
            this.updateGroup(t, isNew);
        }else {
            this.updateLedgerAccount(t);
        }
    }

    private void updateLedgerAccount(ReportData t){
        ledgerAccount.setDebit(ledgerAccount.getDebit().add(t.getDebit()));
        ledgerAccount.setCredit(ledgerAccount.getCredit().add(t.getCredit()));
        this.updateGroup(t,false);
    }

    private void updateGroup(ReportData t, Boolean isNew){
        subGroup.setDebit(subGroup.getDebit().add(t.getDebit()));
        subGroup.setCredit(subGroup.getCredit().add(t.getCredit()));

        primaryGroup.setDebitAmount(primaryGroup.getDebitAmount().add(t.getDebit()));
        primaryGroup.setCreditAmount(primaryGroup.getCreditAmount().add(t.getCredit()));

        if(isNew) {
//            if(subGroup != null && subGroup.getOpeningBalance() != null) {
                subGroup.setOpeningBalance(subGroup.getOpeningBalance().add(t.getOpeningBalance()));
                subGroup.setOpeningCreditBalance(subGroup.getOpeningCreditBalance().add(t.getOpeningCreditBalance()));
//            if(primaryGroup !=null && primaryGroup.getOpeningBalance() != null) {
                primaryGroup.setOpeningBalance(primaryGroup.getOpeningBalance().add(t.getOpeningBalance()));
                primaryGroup.setOpeningCreditBalance(primaryGroup.getOpeningCreditBalance().add(t.getOpeningCreditBalance()));
//            }
            subGroup.getLedgerAccounts().add(ledgerAccount);
//            }
        }
    }

    @Override
    public Map<String,Object> getLedgerStatement(String code, LocalDateTime fromDate, LocalDateTime toDate) {
        Map<String,Object> map = new HashMap<>();
        map.put("ledger",caRepository.findLedgerByCode(code));
        map.put("ledgerStatement",reportRepository.getLedgerStatement(code,fromDate,toDate));
        return map;
    }
}
