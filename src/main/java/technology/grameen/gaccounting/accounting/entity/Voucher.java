package technology.grameen.gaccounting.accounting.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private VoucherType voucherType;

    @Column(length = 100)
    private String voucherNo;

    private LocalDateTime voucherDate;
    private String voucherMode;

    @Column(length = 500)
    private String note;

    private BigDecimal totalDebitAmount;

    private BigDecimal totalCreditAmount;

    private Boolean status;

    private Integer createdBy;
    private Integer updatedBy;

    @Column(nullable = false)
    private Integer officeId;

    @Column(nullable = false)
    private Short officeTypeId;

    @Value("${keycloak.realm")
    @Column(nullable = false)
    private String realm;

    @OneToMany(mappedBy = "voucher",cascade = CascadeType.REMOVE)
    @OrderBy("id ASC")
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "voucher")
    private Set<TransactionReference> transactionReferences;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VoucherType getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(VoucherType voucherType) {
        this.voucherType = voucherType;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public LocalDateTime getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(LocalDateTime voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getVoucherMode() {
        return voucherMode;
    }

    public void setVoucherMode(String voucherMode) {
        this.voucherMode = voucherMode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getTotalDebitAmount() {
        return totalDebitAmount;
    }

    public void setTotalDebitAmount(BigDecimal totalDebitAmount) {
        this.totalDebitAmount = totalDebitAmount;
    }

    public BigDecimal getTotalCreditAmount() {
        return totalCreditAmount;
    }

    public void setTotalCreditAmount(BigDecimal totalCreditAmount) {
        this.totalCreditAmount = totalCreditAmount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
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

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        if(transaction!=null){
            if(this.transactions==null){
                this.transactions = new HashSet<>();
            }

            this.transactions.add(transaction);
        }

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }




}
