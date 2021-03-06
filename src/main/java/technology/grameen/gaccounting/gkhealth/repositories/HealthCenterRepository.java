package technology.grameen.gaccounting.gkhealth.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.gkhealth.entity.HealthCenter;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthCenterRepository extends JpaRepository<HealthCenter,Long> {


    @Query("SELECT c FROM HealthCenter c")
    List<HealthCenter> findAll();

    Page<HealthCenter> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<HealthCenter> findAllByCenterCode(String code , Pageable pageable);

    Page<HealthCenter> findAllByThirdLevel(String thirdLevel, Pageable pageable);

    Page<HealthCenter> findAllByNameContainingIgnoreCaseAndCenterCodeAndThirdLevel(String name,
                                                               String code,
                                                               String thirdLevel,
                                                               Pageable pageable);



    Optional<HealthCenter> findByApiOfficeId(Long apiCenterId);

    List<HealthCenter> findByNameContainingIgnoreCase(String keyword);

    List<HealthCenter> findByThirdLevel(String thirdLevel);

    List<HealthCenter> findByOfficeTypeId(Integer officeTypeId);

    List<HealthCenter> findByOfficeLevel(Integer officeLevel);

    @Query("SELECT c FROM HealthCenter c WHERE c.id = :centerId")
    List<HealthCenter> findCenterById(@Param("centerId") Long id);

    @Query(value = "SELECT LISTAGG(id,',') from health_centers",nativeQuery = true)
    List<String> getAllIds();

    @Query(value = "SELECT LISTAGG(id,',') from health_centers WHERE THIRD_LEVEL=:thirdLevelCode",nativeQuery = true)
    List<String> getAllIds(@Param("thirdLevelCode") String thirdLevelCode);
}
