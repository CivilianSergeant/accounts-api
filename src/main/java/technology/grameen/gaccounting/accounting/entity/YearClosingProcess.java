package technology.grameen.gaccounting.accounting.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "year_closing_processes")
public class YearClosingProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean status;
    private LocalDateTime closingDate;
    private String closingComment;
    private String trialBalancePath;
    private String profitLossPath;
    private String balanceSheetPath;
    private Integer createdBy;
    private Integer closedBy;

    @OneToMany(mappedBy = "process")
    private Set<LedgerBalanceHistory> ledgerBalanceHistories;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public String getClosingComment() {
        return closingComment;
    }

    public void setClosingComment(String closingComment) {
        this.closingComment = closingComment;
    }

    public String getTrialBalancePath() {
        return trialBalancePath;
    }

    public void setTrialBalancePath(String trialBalancePath) {
        this.trialBalancePath = trialBalancePath;
    }

    public String getProfitLossPath() {
        return profitLossPath;
    }

    public void setProfitLossPath(String profitLossPath) {
        this.profitLossPath = profitLossPath;
    }

    public String getBalanceSheetPath() {
        return balanceSheetPath;
    }

    public void setBalanceSheetPath(String balanceSheetPath) {
        this.balanceSheetPath = balanceSheetPath;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(Integer closedBy) {
        this.closedBy = closedBy;
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
