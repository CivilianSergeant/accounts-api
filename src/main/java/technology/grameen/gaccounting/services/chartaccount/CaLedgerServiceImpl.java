package technology.grameen.gaccounting.services.chartaccount;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technology.grameen.gaccounting.accounting.entity.ChartAccountLedger;
import technology.grameen.gaccounting.accounting.repositories.CaLedgerRepository;
import technology.grameen.gaccounting.projection.LedgerBalance;
import technology.grameen.gaccounting.projection.OpeningBalanceDiff;

import java.util.Map;
import java.util.Optional;

@Service
public class CaLedgerServiceImpl implements CaLedgerService{

    CaLedgerRepository caLedgerRepository;

    CaLedgerServiceImpl(CaLedgerRepository caLedgerRepository){
        this.caLedgerRepository = caLedgerRepository;
    }

    @Override
    @Transactional
    public ChartAccountLedger addCaLedger(ChartAccountLedger chartAccountLedger) {
        return caLedgerRepository.save(chartAccountLedger);
    }

    @Override
    public LedgerBalance getBalance(Long id) {
        return caLedgerRepository.getLedgerBalance(id);
    }

    @Override
    public Optional<OpeningBalanceDiff> getOpeningBalanceDifference() {
        return caLedgerRepository.getTotalOpeningBalanceDifference();
    }
}
