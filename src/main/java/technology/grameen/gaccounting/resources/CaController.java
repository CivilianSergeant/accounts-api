package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.ExceptionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.chartaccount.CaService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cas")
public class CaController {

    CaService caService;

    CaController(CaService caService){
        this.caService = caService;
    }

    @PostMapping("/add")
    public ResponseEntity<IResponse> addCa(@RequestBody ChartAccount chartAccount){


        ChartAccount chartAccount1 = null;
        try {
            chartAccount1 = caService.addChartAccount(chartAccount);
        } catch (CustomException e) {
            return new ResponseEntity<>(new ExceptionResponse(
                    HttpStatus.UNPROCESSABLE_ENTITY.value(),
                    e.getMessage()
            ),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        chartAccount1.setChartAccountGroups(null);
        chartAccount1.setChildren(null);
        chartAccount1.setDrHeadMaps(null);
        chartAccount1.setCrHeadMaps(null);

        if(chartAccount1.getChartAccountLedger()!=null) {
            chartAccount1.getChartAccountLedger().setChartAccount(null);
        }
        chartAccount1.getChartAccountType().setChartAccounts(null);

        if(chartAccount1.getParent()!=null){
            chartAccount1.getParent().setChildren(null);
        }

        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                chartAccount1
        ), HttpStatus.OK);

    }

    @ExceptionHandler
    public ResponseEntity<IResponse>  getExecption(Exception ex, HttpServletRequest req){
        return new ResponseEntity<>(new ExceptionResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getMessage()
        ),HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
