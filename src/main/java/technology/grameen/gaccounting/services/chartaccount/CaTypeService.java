package technology.grameen.gaccounting.services.chartaccount;

import technology.grameen.gaccounting.accounting.entity.ChartAccountType;
import technology.grameen.gaccounting.projection.authserver.CaTypeList;

import java.util.List;

public interface CaTypeService {

    List<CaTypeList> chartAccountTypeList();
}
