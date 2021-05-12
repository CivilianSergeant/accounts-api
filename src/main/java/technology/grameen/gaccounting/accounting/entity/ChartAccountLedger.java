package technology.grameen.gaccounting.accounting.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ca_ledgers")
public class ChartAccountLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String contactName;

    @Column(length = 100)
    private String contactEmail;

    @Column(length = 100, nullable = false)
    private String contactPhone;

    @Column(length = 500, nullable = false)
    private String contactAddress;

    private Boolean isMaintainByBill;

    private Boolean isCostCenterApplicable;

    private Boolean isProvideBankDetail;

    private Boolean isRoundingMethod;

    private Integer roundingLimit;

    private BigDecimal currentBalance;

    private BigDecimal openingBalance;

    @OneToOne
    private ChartAccount chartAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Boolean getMaintainByBill() {
        return isMaintainByBill;
    }

    public void setMaintainByBill(Boolean maintainByBill) {
        isMaintainByBill = maintainByBill;
    }

    public Boolean getCostCenterApplicable() {
        return isCostCenterApplicable;
    }

    public void setCostCenterApplicable(Boolean costCenterApplicable) {
        isCostCenterApplicable = costCenterApplicable;
    }

    public Boolean getProvideBankDetail() {
        return isProvideBankDetail;
    }

    public void setProvideBankDetail(Boolean provideBankDetail) {
        isProvideBankDetail = provideBankDetail;
    }

    public Boolean getRoundingMethod() {
        return isRoundingMethod;
    }

    public void setRoundingMethod(Boolean roundingMethod) {
        isRoundingMethod = roundingMethod;
    }

    public Integer getRoundingLimit() {
        return roundingLimit;
    }

    public void setRoundingLimit(Integer roundingLimit) {
        this.roundingLimit = roundingLimit;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(BigDecimal openingBalance) {
        this.openingBalance = openingBalance;
    }

    public ChartAccount getChartAccount() {
        return chartAccount;
    }

    public void setChartAccount(ChartAccount chartAccount) {
        this.chartAccount = chartAccount;
    }
}
