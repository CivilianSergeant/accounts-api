package technology.grameen.gaccounting.services.chartaccount;

import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.accounting.entity.ChartAccountGroup;

public interface CaGroupService {

    ChartAccountGroup addCaGroup(ChartAccount chartAccount);
}
