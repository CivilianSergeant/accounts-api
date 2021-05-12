package technology.grameen.gaccounting.services.chartaccount;

import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.exceptions.CustomException;

public interface CaService {

    ChartAccount addChartAccount(ChartAccount chartAccount) throws CustomException;
}
