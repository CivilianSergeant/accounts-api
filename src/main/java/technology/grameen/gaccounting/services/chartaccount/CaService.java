package technology.grameen.gaccounting.services.chartaccount;

import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.*;

import java.util.List;
import java.util.Optional;

public interface CaService {

    List<ChartAccountList> getChartAccounts();
    List<LedgerAccountList> getLedgerAccounts();
    List<ChartAccountList> getGroupAccounts();

    Optional<GroupDetail> getGroupDetail(Long id);
    Optional<LedgerDetail> getLedgerDetail(Long id);
    LedgerBalance getLedgerBalance(Long id);

    ChartAccount addChartAccount(ChartAccount chartAccount) throws CustomException;
}
