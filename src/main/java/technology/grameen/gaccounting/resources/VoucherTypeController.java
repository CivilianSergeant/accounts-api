package technology.grameen.gaccounting.resources;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.accounting.entity.VoucherType;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.ExceptionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.voucher.VoucherTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/voucher-types")
public class VoucherTypeController {

    private static final Integer PAGE_SIZE=10;

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

    @GetMapping("/list")
    public ResponseEntity<IResponse> getVoucherTypes(@RequestParam Optional<Integer> page,
                                                     @RequestParam Optional<Integer> size,
                                                     @RequestParam Optional<String> sortBy,
                                                     @RequestParam Optional<Boolean> sortDesc){

        Pageable pageable = null;
        try {
            page.orElseThrow(()->new Exception("Query param page missing"));
            size.orElseThrow(()->new Exception("Query param size missing"));
            sortBy.orElseThrow(()->new Exception("Query param sortBy missing"));
            sortDesc.orElseThrow(()->new Exception("Query param sortDesc missing [true or false]"));

        String _sortBy = sortBy.orElse(null);
        _sortBy = (_sortBy.contains("active")) ? "isActive":_sortBy;

        Sort sort = null;
        if(!_sortBy.isEmpty()) {
            sort =   (sortDesc.orElse(false)) ? Sort.by(_sortBy).descending()
                    : Sort.by(_sortBy).ascending();
        }
        pageable = (sort!=null)? PageRequest.of(page.orElse(0),size.orElse(PAGE_SIZE),sort)
                : PageRequest.of(page.orElse(0),size.orElse(PAGE_SIZE));

        } catch (Exception ex) {
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,ex.getMessage()),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                voucherTypeService.getVoucherTypeList(pageable)
        ),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<IResponse> addVoucherType(@RequestBody VoucherType voucherType){

        VoucherType voucherType1 = voucherTypeService.addVoucherType(voucherType);
        voucherType1.setAccMaps(null);
        voucherType1.setAutoVoucherMaps(null);
        voucherType1.setVouchers(null);
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                voucherType1

        ), HttpStatus.OK);
    }

    @GetMapping("/by-alias/{alias}")
    public ResponseEntity<IResponse> getOneByAlias(@PathVariable("alias") String alias){
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                voucherTypeService.findByAlias(alias)
        ),HttpStatus.OK);
    }
}
