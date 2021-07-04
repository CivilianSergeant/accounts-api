package technology.grameen.gaccounting.services.imports;

import org.springframework.web.multipart.MultipartFile;
import technology.grameen.gaccounting.exceptions.CustomException;

import java.io.IOException;

public interface ImportService {

    public final static String TYPE_XSLX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    ImportServiceResponse parseXlsxFile(MultipartFile file) throws IOException, CustomException;



}
