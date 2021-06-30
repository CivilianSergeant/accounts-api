package technology.grameen.gaccounting.accounting.entity.imports;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "import_ledgers")
public class ImportLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String filename;

    @OneToMany(mappedBy = "importLedger")
    private Set<LedgerInfo> ledgerInfos;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
