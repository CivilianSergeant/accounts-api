package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.MenuPermission;

@Repository
public interface MenuPermissionRepository extends JpaRepository<MenuPermission,Long> {
}
