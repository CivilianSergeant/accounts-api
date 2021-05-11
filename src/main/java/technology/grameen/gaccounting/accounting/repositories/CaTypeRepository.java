package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.ChartAccountType;
import technology.grameen.gaccounting.projection.authserver.CaTypeList;

import java.util.List;

@Repository
public interface CaTypeRepository extends JpaRepository<ChartAccountType,Integer> {

    List<CaTypeList> findAllByStatus(Boolean status);
}

