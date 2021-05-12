package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.ChartAccountLedger;

@Repository
public interface CaLedgerRepository extends JpaRepository<ChartAccountLedger,Long> {
}
