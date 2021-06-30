package technology.grameen.gaccounting.services.rolepermission;

import org.springframework.stereotype.Service;
import technology.grameen.gaccounting.accounting.repositories.MenuPermissionRepository;
import technology.grameen.gaccounting.requests.RolePermission;

@Service
public class RolePermissionServiceImpl implements RolePermissionService{

    private MenuPermissionRepository menuPermissionRepository;

    RolePermissionServiceImpl(MenuPermissionRepository menuPermissionRepository){
        this.menuPermissionRepository = menuPermissionRepository;
    }

    @Override
    public Boolean saveRolePermission(RolePermission rolePermission) {
        try {
            this.menuPermissionRepository.saveAll(rolePermission.getPermissions());
        }catch(Exception ex){
            throw new RuntimeException("Sorry! try again later");
        }
        return true;
    }
}
