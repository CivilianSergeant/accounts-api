package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.ExceptionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.responses.SimpleResponse;
import technology.grameen.gaccounting.services.chartaccount.CaImportService;
import technology.grameen.gaccounting.services.imports.ImportService;
import technology.grameen.gaccounting.services.imports.ImportServiceResponse;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/import")
public class ImportController {

    ImportService importService;
    CaImportService caImportService;

    public ImportController(ImportService importService,
                            CaImportService caImportService) {
        this.importService = importService;
        this.caImportService = caImportService;
    }

    @PostMapping("/upload")
    public ResponseEntity<IResponse> importFile(@RequestParam("file") MultipartFile file) throws IOException, CustomException {
        if(!file.getContentType().equalsIgnoreCase(ImportService.TYPE_XSLX)){
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Sorry! It's not a valid Xlsx File"),HttpStatus.OK);
        }
        ImportServiceResponse importServiceResponse = importService.parseXlsxFile(file);
        int count = caImportService.importLedgerAccount(importServiceResponse);

        return new ResponseEntity<>(new SimpleResponse(
                HttpStatus.OK.value(),
                "Total "+ count + " record imported"
        ),HttpStatus.OK);
    }
}
