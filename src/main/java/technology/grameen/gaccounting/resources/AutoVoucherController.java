package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technology.grameen.gaccounting.accounting.entity.AutoVoucherMap;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.IResponse;

@RestController
@RequestMapping(value = "/api/v1/auto-voucher")
public class AutoVoucherController {

    @PostMapping(value = "/add")
    public ResponseEntity<IResponse> addVoucher(@RequestBody AutoVoucherMap voucherMap){

        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                null
        ), HttpStatus.OK);
    }
}
