package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.GeneralSetting;

import java.util.Optional;

@Repository
public interface GeneralSettingRepository extends JpaRepository<GeneralSetting,Long> {

    Optional<GeneralSetting> findByAlias(String alias);

}
