package technology.grameen.gaccounting.services.report;

import java.util.List;

public interface ReportService {

    List<CaType> getTrialBalance();

    List<CaType> getIncomeStatement();
}
