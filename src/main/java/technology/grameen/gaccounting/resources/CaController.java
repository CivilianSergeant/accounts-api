package technology.grameen.gaccounting.resources;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.accounting.entity.ChartAccount;
import technology.grameen.gaccounting.exceptions.CustomException;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.ExceptionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.chartaccount.CaService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cas")
public class CaController {

    private static final Integer PAGE_SIZE = 10;
    CaService caService;

    CaController(CaService caService){
        this.caService = caService;
    }


    @GetMapping("")
    public ResponseEntity<IResponse> getChartAccounts(){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(),
                caService.getChartAccounts()
        ), HttpStatus.OK);
    }

    @GetMapping("/ledgers")
    public ResponseEntity<IResponse> getLedgerAccounts(){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(),
                caService.getLedgerAccounts()
        ), HttpStatus.OK);
    }

    @GetMapping("/ledgers/{keyword}")
    public ResponseEntity<IResponse> getLedgerAccounts(@PathVariable("keyword") String keyword){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(),
                caService.getLedgerAccounts(keyword)
        ), HttpStatus.OK);
    }

    @GetMapping("/ledgers/list")
    public ResponseEntity<IResponse> getLedgerAccounts(
                                                       @RequestParam(value = "type") Optional<String> type,
                                                       @RequestParam(value = "title") Optional<String> title,
                                                       @RequestParam(value = "code") Optional<String> code,
                                                       @RequestParam(value = "page") Optional<Integer> page,
                                                       @RequestParam(value = "size") Optional<Integer> size,
                                                       @RequestParam(value = "sortBy") Optional<String> sortBy,
                                                       @RequestParam(value = "sortDesc") Optional<Boolean> sortDesc){

        Pageable pageable = null;
        try {
            page.orElseThrow(()->new Exception("Query param page missing"));
            size.orElseThrow(()->new Exception("Query param size missing"));
            sortBy.orElseThrow(()->new Exception("Query param sortBy missing"));
            sortDesc.orElseThrow(()->new Exception("Query param sortDesc missing [true or false]"));

            String _sortBy = sortBy.orElse(null);
            //_sortBy = (_sortBy.contains("active")) ? "isActive":_sortBy;

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
                caService.getLedgerAccounts(type.get(), title.get(), code.get(), pageable)
        ), HttpStatus.OK);
    }

    @GetMapping("/groups")
    public ResponseEntity<IResponse> getGroupAccounts(@RequestParam("title") String title){
        return new ResponseEntity<>(new EntityCollectionResponse<>(
                HttpStatus.OK.value(),
                caService.getAllGroupAccounts(title)
        ), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<IResponse> addCa(@Valid @RequestBody ChartAccount chartAccount) throws Exception {


        ChartAccount chartAccount1 = null;

        chartAccount1 = caService.addChartAccount(chartAccount);

        chartAccount1.setChartAccountGroups(null);
        chartAccount1.setChildren(null);
        chartAccount1.setDrHeadMaps(null);
        chartAccount1.setCrHeadMaps(null);

        if(chartAccount1.getChartAccountLedger()!=null) {
            chartAccount1.getChartAccountLedger().setChartAccount(null);
        }
        chartAccount1.setChartAccountType(null);
//
//        if(chartAccount1.getParent()!=null){
//            chartAccount1.getParent().setChildren(null);
//            chartAccount1.getParent().setChartAccountType(null);
//        }

        chartAccount1.setParent(null);

        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                chartAccount1
        ), HttpStatus.OK);

    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<IResponse> getGroupDetail(@PathVariable("id") Long id){

        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                caService.getGroupDetail(id)
        ), HttpStatus.OK);
    }

    @GetMapping("/ledger/detail/{id}")
    public ResponseEntity<IResponse> getLedgerDetail(@PathVariable("id") Long id){

        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                caService.getLedgerDetail(id)
        ), HttpStatus.OK);
    }

    @GetMapping("/ledger/balance/{id}")
    public ResponseEntity<IResponse> getLedgerBalance(@PathVariable("id") Long id){
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                caService.getLedgerBalance(id)
        ), HttpStatus.OK);
    }

    @GetMapping("/ledger/opening-balance-diff")
    public ResponseEntity<IResponse> getOpeningBalanceDifference(){
        return new ResponseEntity<>(new EntityResponse<>(
                HttpStatus.OK.value(),
                caService.getOpeningBalanceDiff()
        ), HttpStatus.OK);
    }


}
