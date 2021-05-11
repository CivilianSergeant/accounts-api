package technology.grameen.gaccounting.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.voucher.VoucherTypeService;

@RestController
@RequestMapping("/api/v1/voucher-types")
public class VoucherTypeController {

    VoucherTypeService voucherTypeService;

    VoucherTypeController(VoucherTypeService voucherTypeService){
        this.voucherTypeService = voucherTypeService;
    }

    @GetMapping("")
    public ResponseEntity<IResponse> getVoucherTypeList(){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(),
                voucherTypeService.getVoucherTypeList()
        ), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<IResponse> addVoucherType(@RequestBody VoucherType voucherType){
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                voucherTypeService.addVoucherType(voucherType)
        ), HttpStatus.OK);
    }
}
