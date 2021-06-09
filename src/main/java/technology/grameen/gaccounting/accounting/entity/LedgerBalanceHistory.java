package technology.grameen.gaccounting.accounting.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ledger_balance_histories")
public class LedgerBalanceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private YearClosingProcess process;

    @ManyToOne
    private ChartAccount chartAccount;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;

    private BigDecimal balance;

    private LocalDate closingDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonBackReference(value = "process")
    public YearClosingProcess getProcess() {
        return process;
    }

    public void setProcess(YearClosingProcess process) {
        this.process = process;
    }

    @JsonBackReference(value = "chartAccount")
    public ChartAccount getChartAccount() {
        return chartAccount;
    }

    public void setChartAccount(ChartAccount chartAccount) {
        this.chartAccount = chartAccount;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }
}
