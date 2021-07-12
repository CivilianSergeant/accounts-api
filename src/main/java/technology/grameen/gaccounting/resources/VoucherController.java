package technology.grameen.gaccounting.resources;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.accounting.entity.Voucher;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.projection.VoucherDetail;
import technology.grameen.gaccounting.requests.AutoVoucherRequest;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.ExceptionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.voucher.VoucherService;
import technology.grameen.gaccounting.services.voucher.auto.AutoVoucherService;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vouchers")
public class VoucherController {

    private static final Integer PAGE_SIZE=10;

    VoucherService voucherService;
    AutoVoucherService autoVoucherService;

    VoucherController(VoucherService voucherService,
                      AutoVoucherService autoVoucherService){
        this.voucherService = voucherService;
        this.autoVoucherService = autoVoucherService;
    }

    @GetMapping("/list")
    public ResponseEntity<IResponse> getVouchers(@RequestParam Optional<Integer> page,
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
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,ex.getMessage()),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                voucherService.getVouchers(pageable)
        ), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<IResponse> addVoucher(@Valid @RequestBody Voucher voucher) throws Exception{


            Voucher voucher1 = voucherService.addVoucher(voucher);
            voucher1.getTransactions().stream().map(t->{
                t.setVoucher(null);
                return t;
            }).collect(Collectors.toSet());

            return new ResponseEntity<>(new EntityResponse<>(HttpStatus.OK.value(),voucher1),HttpStatus.OK);

    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<IResponse> getVoucherDetail(@PathVariable("id") Long id){
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                voucherService.getVoucherDetail(id)
        ),HttpStatus.OK);
    }


    @PostMapping("/auto")
    public ResponseEntity<IResponse> addAutoVoucher(@RequestBody AutoVoucherRequest req) throws CustomException {
        autoVoucherService.setVoucherService(voucherService);
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                autoVoucherService.saveAutoVoucher(req)
        ),HttpStatus.OK);
    }

    @GetMapping("/by-number/{number}")
    public ResponseEntity<IResponse> isVoucherNoAvailable(@PathVariable("number") String number){
        List<VoucherDetail> voucherExist = voucherService.isVoucherNoUnique(number);

        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                voucherExist.size()
        ), HttpStatus.OK);
    }




}
