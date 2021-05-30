package technology.grameen.gaccounting.services.chartaccount;

import technology.grameen.gaccounting.accounting.entity.ChartAccountLedger;
import technology.grameen.gaccounting.projection.LedgerBalance;

public interface CaLedgerService {

    ChartAccountLedger addCaLedger(ChartAccountLedger chartAccountLedger);
    LedgerBalance getBalance(Long id);
}
