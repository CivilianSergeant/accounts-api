package technology.grameen.gaccounting.services.report;

import technology.grameen.gaccounting.projection.TrialBalance;

import java.util.List;

public interface ReportService {

    List<CaType> getTrialBalance();
}
