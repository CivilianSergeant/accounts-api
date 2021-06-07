package technology.grameen.gaccounting.services.report;

import java.util.List;
import java.util.Map;

public interface ReportService {

    List<CaType> getTrialBalance();

    Map<String, Object> getIncomeStatement();
}
