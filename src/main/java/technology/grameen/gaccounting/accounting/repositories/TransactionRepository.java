package technology.grameen.gaccounting.accounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technology.grameen.gaccounting.accounting.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
