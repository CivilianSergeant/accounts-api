package technology.grameen.gaccounting.resources.authserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technology.grameen.gaccounting.network.HttpClient;
import technology.grameen.gaccounting.projection.authserver.AdminAcessToken;
import technology.grameen.gaccounting.requests.RolePermission;
import technology.grameen.gaccounting.responses.EntityCollectionResponse;
import technology.grameen.gaccounting.responses.IResponse;
import technology.grameen.gaccounting.responses.SimpleResponse;
import technology.grameen.gaccounting.services.rolepermission.RolePermissionService;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private HttpClient httpClient;
    private RolePermissionService rolePermissionService;

    RoleController(HttpClient httpClient, RolePermissionService rolePermissionService){
        this.httpClient = httpClient;
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping("")
    public ResponseEntity<IResponse> getRoles(){

        AdminAcessToken adminAccessToken = httpClient.getAdminAccessToken();
        List realmRoles = httpClient.getRealmRoles(adminAccessToken);
        AdminAcessToken adminAccessToken1 = httpClient.getAdminAccessToken();
        List clientRoles = httpClient.getClientRoles(adminAccessToken1);
        Stream<Object> combineResult = Stream.of(realmRoles,clientRoles).flatMap(Collection::stream);
        List<Object> combinedCollection = combineResult.collect(Collectors.toList());
        return new ResponseEntity<>(new EntityCollectionResponse<>(HttpStatus.OK.value(), combinedCollection),
                HttpStatus.OK);
    }

    @PostMapping("/permissions")
    public ResponseEntity<IResponse> setRolePermission(@RequestBody RolePermission rolePermission){
        rolePermissionService.saveRolePermission(rolePermission);
        return new ResponseEntity<>(new SimpleResponse(HttpStatus.OK.value(),
                    "Roles permission changes are saved"),HttpStatus.OK);
    }
}
