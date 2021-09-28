package technology.grameen.gaccounting.services.report;

import technology.grameen.gaccounting.accounting.repositories.CaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ReportService {

    List<CaType> getTrialBalance();

    Map<String, Object> getIncomeStatement();
    Map<String, Object> getBalanceSheet();

    Double getLedgerOpeningBalance(CaRepository.LedgerInfo ledgerInfo, LocalDateTime fromDate);

    Map<String,Object> getLedgerStatement(String code, LocalDateTime fromDate, LocalDateTime toDate);
}
