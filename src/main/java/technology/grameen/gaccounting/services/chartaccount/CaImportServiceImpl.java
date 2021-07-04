package technology.grameen.gaccounting.services.chartaccount;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.accounting.entity.ChartAccountLedger;
import technology.grameen.gaccounting.accounting.entity.ChartAccountType;
import technology.grameen.gaccounting.accounting.entity.imports.LedgerInfo;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.CaTypeList;
import technology.grameen.gaccounting.services.imports.ImportServiceResponse;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CaImportServiceImpl implements CaImportService{

    CaService caService;
    CaTypeService caTypeService;

    public CaImportServiceImpl(CaService caService,CaTypeService caTypeService) {
        this.caService = caService;
        this.caTypeService = caTypeService;
    }

    @Override
    @Transactional
    public int importLedgerAccount(ImportServiceResponse importServiceResponse) throws CustomException {

        List<CaTypeList> caTypeList = caTypeService.chartAccountTypeList();
        Map<String,ChartAccount> parentGroupCode = new HashMap<>();
        AtomicInteger count= new AtomicInteger();
        for (LedgerInfo info : importServiceResponse.getLedgerInfoList()) {
            ChartAccount parentChartAccount = null;
            if (!parentGroupCode.containsKey(info.getParentGroupCode())) {
                Optional<ChartAccount> parentAccount = caService.getChartAccountByParentCode(info.getParentGroupCode());
                if (!parentAccount.isPresent()) {
                    throw new CustomException("Parent not found with code [" + info.getParentGroupCode() + "]");
                }
                parentChartAccount = parentAccount.get();
                parentGroupCode.put(info.getParentGroupCode(), parentChartAccount);
            } else {
                parentChartAccount = parentGroupCode.get(info.getParentGroupCode());
            }

            Optional<CaTypeList> caTypeOp = caTypeList.stream().filter(ct -> info.getChartAccountType()
                    .toLowerCase().equalsIgnoreCase(ct.getAlias())).findFirst();

            if (!caTypeOp.isPresent()) {
                throw new CustomException("Chart Account Type ["+info.getChartAccountType()+"] not found");
            }

            ChartAccount ca = new ChartAccount();
            ChartAccountLedger chartAccountLedger = new ChartAccountLedger();
            chartAccountLedger.setContactName(info.getLedgerName());
            ca.setChartAccountLedger(chartAccountLedger);
            ca.setTitle(info.getLedgerName());
            ca.setDescription(info.getLedgerName());
            ca.setCode(info.getLedgerCode());
            ca.setCaLevel(Short.valueOf(info.getCaLevel()));
            ca.setParent(parentChartAccount);
            ChartAccountType chartAccountType = new ChartAccountType();
            chartAccountType.setId(caTypeOp.get().getId());
            ca.setChartAccountType(chartAccountType);
            ca.setLedger(true);
            ca.setSortOrder(1);
            ca.setStatus(true);

            caService.addChartAccount(ca);
            if (ca.getId() > 0) {
                count.getAndIncrement();
            }

        }
        return count.get();
    }
}
