package technology.grameen.gaccounting.accounting.entity.imports;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "imported_ledger_info")
public class LedgerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ImportLedger importLedger;

    private String chartAccountType;
    private String caLevel;
    private String parentGroupCode;
    private String ledgerName;
    private String ledgerCode;
    private BigDecimal openingBalanceDr;
    private BigDecimal openingBalanceCr;



}
