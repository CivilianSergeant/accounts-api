package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.Menu;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

    @Query("SELECT distinct m from Menu m LEFT JOIN FETCH m.children c JOIN FETCH m.permissions p ORDER BY m.displayOrder ASC")
    List<Menu> findAllOrderByDisplayOrderAsc();

    @Query("SELECT m from Menu m LEFT JOIN FETCH m.children c JOIN FETCH m.permissions p WHERE p.role=:role" +
            " ORDER BY m.displayOrder ASC")
    List<Menu> findByRole(@Param("role") String role);


}
