package technology.grameen.gaccounting.services.chartaccount;

import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.authserver.ChartAccountList;

import java.util.List;

public interface CaService {

    List<ChartAccountList> getChartAccounts();

    ChartAccount addChartAccount(ChartAccount chartAccount) throws CustomException;
}
