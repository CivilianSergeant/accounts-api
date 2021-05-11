package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.chartaccount.CaTypeService;

@RestController
@RequestMapping("/api/v1/ca-types")
public class CaTypeController {

    CaTypeService caTypeService;

    CaTypeController(CaTypeService caTypeService){
        this.caTypeService = caTypeService;
    }

    @GetMapping("")
    public ResponseEntity<IResponse> getCaTypeList(){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(),
                caTypeService.chartAccountTypeList()
        ),HttpStatus.OK);
    }
}
