package technology.grameen.gaccounting.requests;



import technology.grameen.gaccounting.accounting.entity.MenuPermission;

import java.util.Set;

public class RolePermission {
    Set<MenuPermission> permissions;

    public Set<MenuPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<MenuPermission> permissions) {
        this.permissions = permissions;
    }
}
