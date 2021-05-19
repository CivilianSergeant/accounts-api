package technology.grameen.gaccounting.resources;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.gkhealth.entity.HealthCenter;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.EntityResponse;
import technology.grameen.gaccounting.responses.ExceptionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.services.HealthCenterService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api/v1/health-center")
public class HealthCenterController {

    private final Integer PAGE_SIZE = 10;

    private HealthCenterService healthCenterService;

    public HealthCenterController(HealthCenterService healthCenterService){
        this.healthCenterService = healthCenterService;
    }

    @RequestMapping(value = "/list")
    public ResponseEntity<IResponse> list(
                            @RequestParam Optional<String> thirdLevel,
                            @RequestParam Optional<String> name,
                            @RequestParam Optional<String> code,
                            @RequestParam Optional<Integer> page,
                            @RequestParam Optional<Integer> size,
                            @RequestParam Optional<String> sortBy,
                            @RequestParam Optional<Boolean> sortDesc){

        String _sortBy = sortBy.orElse(null);

        Sort sort = null;

        if(!_sortBy.isEmpty()) {
            sort =   (sortDesc.orElse(false)) ? Sort.by(_sortBy).descending()
                    : Sort.by(_sortBy).ascending();
        }
        Pageable pageable = (sort!=null)? PageRequest.of(page.orElse(0),size.orElse(PAGE_SIZE),sort)
                : PageRequest.of(page.orElse(0),size.orElse(PAGE_SIZE));

        return new ResponseEntity<>(new EntityResponse<>(HttpStatus.OK.value(),
                healthCenterService.getCenters(thirdLevel.orElse(""),
                        name.orElse(""),
                        code.orElse(""),
                        pageable)),HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<HealthCenter>> list(){
        return new ResponseEntity<>(healthCenterService.getCenters(),HttpStatus.OK);
    }

    @GetMapping(value = "/ra-office")
    public ResponseEntity<List<HealthCenter>> getRaOffice(){
        return new ResponseEntity<>(healthCenterService.getRaCenters(),HttpStatus.OK);
    }

    @GetMapping(value = "/hc-office")
    public ResponseEntity<List<HealthCenter>> getHcOfficeByRaOffice(@RequestParam Optional<String> raOfficeCode){
        return new ResponseEntity<>(
                healthCenterService.getCentersByThirdLevel(raOfficeCode.orElse(""))
        ,HttpStatus.OK);
    }

    @GetMapping("/api-id/{apiCenterId}")
    public ResponseEntity<IResponse> getCenterByApiCenterId(@PathVariable("apiCenterId") Long apiCenterId){
        return new ResponseEntity<>(new EntityResponse(HttpStatus.OK.value(),
                healthCenterService.getCenterByApiCenterId(apiCenterId)),HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<IResponse> addCenter(@RequestBody HealthCenter req){
        if(req.getName().isEmpty()){
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Center/Office Name Required"),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(req.getCenterCode().isEmpty()){
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Center/Office Code Required"),HttpStatus.UNPROCESSABLE_ENTITY);
        }

        HealthCenter center = healthCenterService.addCenter(req);
        return new ResponseEntity<>(new EntityResponse<>(HttpStatus.OK.value(),center), HttpStatus.OK);
    }

    @PutMapping(value = "/add")
    public ResponseEntity<IResponse> updateCenter(@RequestBody HealthCenter req){
        if(req.getId()==null){
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Center/Office id required to update"),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(req.getName().isEmpty()){
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Center/Office Name Required"),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(req.getCenterCode().isEmpty()){
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY,
                    "Center/Office Code Required"),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        HealthCenter center = null;
        try {
            center = healthCenterService.updateCenter(req);
        }catch (RuntimeException ex){
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new EntityResponse<>(HttpStatus.OK.value(),center), HttpStatus.OK);
    }

    @GetMapping("/by-keyword/{keyword}")
    public ResponseEntity<IResponse> getCentersByKeyword(@PathVariable("keyword") String keyword){
        return new ResponseEntity<>(new EntityCollectionResponse<>(HttpStatus.OK.value(),
                healthCenterService.getCentersByKeyword(keyword)),HttpStatus.OK);
    }

    @GetMapping("/by-office-type/{officeTypeId}")
    public ResponseEntity<IResponse> getCentersByOfficeTypeId(@PathVariable("officeTypeId") Integer officeTypeId){
        return new ResponseEntity<>(new EntityCollectionResponse<>(HttpStatus.OK.value(),
                healthCenterService.getCentersByOfficeTypeId(officeTypeId)),HttpStatus.OK);
    }

    @GetMapping("/by-office-level/{officeLevel}")
    public ResponseEntity<IResponse> getCentersByOfficeLevel(@PathVariable("officeLevel") Integer officeLevel){
        return new ResponseEntity<>(new EntityCollectionResponse<>(HttpStatus.OK.value(),
                healthCenterService.getCentersByOfficeLevel(officeLevel)),HttpStatus.OK);
    }
}
