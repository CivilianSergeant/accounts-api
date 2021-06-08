package technology.grameen.gaccounting.services.chartaccount;

import technology.grameen.gaccounting.accounting.entity.ChartAccountLedger;
import technology.grameen.gaccounting.projection.LedgerBalance;
import technology.grameen.gaccounting.projection.OpeningBalanceDiff;

import java.util.Optional;

public interface CaLedgerService {

    ChartAccountLedger addCaLedger(ChartAccountLedger chartAccountLedger);
    LedgerBalance getBalance(Long id);
    Optional<OpeningBalanceDiff> getOpeningBalanceDifference();
}
