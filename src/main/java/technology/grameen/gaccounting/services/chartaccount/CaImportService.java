package technology.grameen.gaccounting.services.chartaccount;

import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.services.imports.ImportServiceResponse;

public interface CaImportService {

    int importLedgerAccount(ImportServiceResponse importServiceResponse) throws CustomException;

}
