package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.report.ReportService;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    private ReportService reportService;

    ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping("/trial-balance")
    public ResponseEntity<IResponse> getTrialBalance(){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(),
                reportService.getTrialBalance()
        ),HttpStatus.OK);
    }
}
