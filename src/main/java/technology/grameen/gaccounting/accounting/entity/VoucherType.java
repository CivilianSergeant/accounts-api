package technology.grameen.gaccounting.accounting.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "voucher_types")
public class VoucherType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100,nullable = false)
    private String name;

    @Column(length = 100,nullable = false)
    private String alias;

    @Column(length = 100)
    private String voucherTypeCode;

    @Column(length = 100)
    private String voucherNumberType="none";

    @Column(length = 20)
    private String numberPrefix;

    @Column(length = 500)
    private String description;

    @Column(length = 150)
    private String moduleName = "accounting";

    @Column(nullable = false)
    private Integer sortOrder=999;

    private Boolean status;

    private Integer createdBy;
    private Integer updatedBy;

    @OneToMany(mappedBy = "voucherType")
    private Set<Voucher> vouchers;

    @OneToMany(mappedBy = "voucherType")
    private Set<AutoVoucherMap> autoVoucherMaps;

    @OneToMany(mappedBy = "voucherType")
    private Set<AccMap> accMaps;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getVoucherTypeCode() {
        return voucherTypeCode;
    }

    public void setVoucherTypeCode(String voucherTypeCode) {
        this.voucherTypeCode = voucherTypeCode;
    }

    public String getVoucherNumberType() {
        return voucherNumberType;
    }

    public void setVoucherNumberType(String voucherNumberType) {
        this.voucherNumberType = voucherNumberType;
    }

    public String getNumberPrefix() {
        return numberPrefix;
    }

    public void setNumberPrefix(String numberPrefix) {
        this.numberPrefix = numberPrefix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    public Set<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(Set<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

    public Set<AutoVoucherMap> getAutoVoucherMaps() {
        return autoVoucherMaps;
    }

    public void setAutoVoucherMaps(Set<AutoVoucherMap> autoVoucherMaps) {
        this.autoVoucherMaps = autoVoucherMaps;
    }

    public Set<AccMap> getAccMaps() {
        return accMaps;
    }

    public void setAccMaps(Set<AccMap> accMaps) {
        this.accMaps = accMaps;
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
