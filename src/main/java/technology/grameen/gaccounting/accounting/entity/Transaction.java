package technology.grameen.gaccounting.accounting.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChartAccount chartAccount;

    @ManyToOne
    private Voucher voucher;

    private BigDecimal amount;

    @Column(length = 2)
    private String transactionType;

    private String narration;

    private BigDecimal balance;

    private Integer officeId;

    private Short officeTypeId;

    private LocalDateTime transactionDate;

    @Value("${keycloak.realm")
    private String realm;

    @OneToMany(mappedBy = "transaction")
    private Set<TransactionReference> transactionReferences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChartAccount getChartAccount() {
        return chartAccount;
    }

    public void setChartAccount(ChartAccount chartAccount) {
        this.chartAccount = chartAccount;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public Short getOfficeTypeId() {
        return officeTypeId;
    }

    public void setOfficeTypeId(Short officeTypeId) {
        this.officeTypeId = officeTypeId;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Set<TransactionReference> getTransactionReferences() {
        return transactionReferences;
    }

    public void setTransactionReferences(Set<TransactionReference> transactionReferences) {
        this.transactionReferences = transactionReferences;
    }
}
