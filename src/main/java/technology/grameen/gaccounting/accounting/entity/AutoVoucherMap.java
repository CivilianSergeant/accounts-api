package technology.grameen.gaccounting.accounting.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "auto_voucher_maps")
public class AutoVoucherMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String alias;
    private String moduleName;
    private String description;

    @ManyToOne
    private VoucherType voucherType;

    @ManyToOne
    private ChartAccount crHeader;

    private String crHeaderInfo;


    @ManyToOne
    private ChartAccount drHeader;

    private String drHeaderInfo;

    private Integer sortOrder;

    private Boolean status;


    private Integer createdBy;
    private Integer updatedBy;

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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VoucherType getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(VoucherType voucherType) {
        this.voucherType = voucherType;
    }

    public ChartAccount getCrHeader() {
        return crHeader;
    }

    public void setCrHeader(ChartAccount crHeader) {
        this.crHeader = crHeader;
    }

    public String getCrHeaderInfo() {
        return crHeaderInfo;
    }

    public void setCrHeaderInfo(String crHeaderInfo) {
        this.crHeaderInfo = crHeaderInfo;
    }

    public ChartAccount getDrHeader() {
        return drHeader;
    }

    public void setDrHeader(ChartAccount drHeader) {
        this.drHeader = drHeader;
    }

    public String getDrHeaderInfo() {
        return drHeaderInfo;
    }

    public void setDrHeaderInfo(String drHeaderInfo) {
        this.drHeaderInfo = drHeaderInfo;
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
