package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.responses.IResponse;

@RestController
@RequestMapping("/api/v1/cas")
public class CaController {



    @PostMapping("/add")
    public ResponseEntity<IResponse> addCa(ChartAccount chartAccount){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
