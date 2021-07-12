package technology.grameen.gaccounting.services.chartaccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.*;
import technology.grameen.gaccounting.services.imports.ImportServiceResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CaService {

    List<ChartAccountList> getChartAccounts();
    List<LedgerAccountList> getLedgerAccounts();
    Page<LedgerAccountList> getLedgerAccounts(String type, String title, String code, Pageable pageable);
    List<ChartAccountList> getGroupAccounts();

    List<ChartAccountList> getAllGroupAccounts(String title);

    Optional<GroupDetail> getGroupDetail(Long id);
    Optional<LedgerDetail> getLedgerDetail(Long id);
    LedgerBalance getLedgerBalance(Long id);
    Optional<OpeningBalanceDiff> getOpeningBalanceDiff();

    ChartAccount addChartAccount(ChartAccount chartAccount) throws CustomException;


    Optional<ChartAccount> getChartAccountByParentCode(String parentGroupCode);

    List<LedgerAccountList> getLedgerAccounts(String keyword);
}
