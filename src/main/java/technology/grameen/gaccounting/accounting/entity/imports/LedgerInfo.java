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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImportLedger getImportLedger() {
        return importLedger;
    }

    public void setImportLedger(ImportLedger importLedger) {
        this.importLedger = importLedger;
    }

    public String getChartAccountType() {
        return chartAccountType;
    }

    public void setChartAccountType(String chartAccountType) {
        this.chartAccountType = chartAccountType;
    }

    public String getCaLevel() {
        return caLevel;
    }

    public void setCaLevel(String caLevel) {
        this.caLevel = caLevel;
    }

    public String getParentGroupCode() {
        return parentGroupCode;
    }

    public void setParentGroupCode(String parentGroupCode) {
        this.parentGroupCode = parentGroupCode;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getLedgerCode() {
        return ledgerCode;
    }

    public void setLedgerCode(String ledgerCode) {
        this.ledgerCode = ledgerCode;
    }

    public BigDecimal getOpeningBalanceDr() {
        return openingBalanceDr;
    }

    public void setOpeningBalanceDr(BigDecimal openingBalanceDr) {
        this.openingBalanceDr = openingBalanceDr;
    }

    public BigDecimal getOpeningBalanceCr() {
        return openingBalanceCr;
    }

    public void setOpeningBalanceCr(BigDecimal openingBalanceCr) {
        this.openingBalanceCr = openingBalanceCr;
    }
}
