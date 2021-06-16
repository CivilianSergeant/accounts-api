package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.accounting.entity.AutoVoucherMap;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.voucher.auto.AutoVoucherService;

@RestController
@RequestMapping(value = "/api/v1/auto-voucher")
public class AutoVoucherController {

    private AutoVoucherService autoVoucherService;

    public AutoVoucherController(AutoVoucherService autoVoucherService) {
        this.autoVoucherService = autoVoucherService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<IResponse> addVoucher(@RequestBody AutoVoucherMap voucherMap) throws CustomException {

        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                autoVoucherService.addVoucherMap(voucherMap)
        ), HttpStatus.OK);
    }

    @GetMapping(value = "/by-alias/{module}/{alias}")
    public ResponseEntity<IResponse> getMapByAlias(@PathVariable("module") String module,
                                                   @PathVariable("alias") String alias){
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                autoVoucherService.getByAlias(module,alias)
        ), HttpStatus.OK);
    }
}
