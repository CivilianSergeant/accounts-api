package technology.grameen.gaccounting.resources;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.accounting.entity.AutoVoucherMap;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.ExceptionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.voucher.auto.AutoVoucherService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/auto-vouchers")
public class AutoVoucherController {

    private static final Integer PAGE_SIZE = 10;
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

    @GetMapping(value = "/by-module/{module}")
    public ResponseEntity<IResponse> getMapByModule(@PathVariable("module") String module){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(),
                autoVoucherService.getByModule(module)
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

    @GetMapping(value = "")
    public ResponseEntity<IResponse> getMappings(@RequestParam Optional<Integer> page,
                                                 @RequestParam Optional<Integer> size,
                                                 @RequestParam Optional<String> sortBy,
                                                 @RequestParam Optional<Boolean> sortDesc){

        Pageable pageable = null;

        try{
            page.orElseThrow(()-> new CustomException("Query parameter page is missing"));
            size.orElseThrow(()-> new CustomException("Query parameter size is missing"));
            sortBy.orElseThrow(()-> new CustomException("Query parameter sortBy is missing"));
            sortDesc.orElseThrow(()-> new CustomException("Query parameter sortDesc is missing"));

            String _sortBy = sortBy.orElse(null);
            _sortBy = (_sortBy.contains("active")) ? "isActive":_sortBy;

            Sort sort = null;
            if(!_sortBy.isEmpty()) {
                sort =   (sortDesc.orElse(false)) ? Sort.by(_sortBy).descending()
                        : Sort.by(_sortBy).ascending();
            }
            pageable = (sort!=null)? PageRequest.of(page.orElse(0),size.orElse(PAGE_SIZE),sort)
                    : PageRequest.of(page.orElse(0),size.orElse(PAGE_SIZE));



        }catch (Exception ex){
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,ex.getMessage()),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(new EntityResponse<>(HttpStatus.OK.value(),
                autoVoucherService.getMappings(pageable)),HttpStatus.OK);

    }
}
