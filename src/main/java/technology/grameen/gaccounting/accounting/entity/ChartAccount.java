package technology.grameen.gaccounting.accounting.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "chart_accounts",uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class ChartAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title should not be blank")
    private String title;

    @NotBlank(message = "Code should not be blank")
    private String code;
    private String description;
    private Integer sortOrder;

    @NotNull(message = "ledger status value missing")
    private Boolean isLedger;
    private Boolean status;
    private Short caLevel;

    @ManyToOne
    private ChartAccount parent;

    @OneToMany(mappedBy = "parent")
    private Set<ChartAccount> children;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false,name = "chart_account_type_id",referencedColumnName = "id")
    private ChartAccountType chartAccountType;

    @OneToOne(mappedBy = "chartAccount")
    private ChartAccountGroup chartAccountGroup;

    @OneToMany(mappedBy = "crHeader")
    private Set<AutoVoucherMap> crHeadMaps;

    @OneToMany(mappedBy = "drHeader")
    private Set<AutoVoucherMap> drHeadMaps;

    @OneToOne(mappedBy = "chartAccount")
    private ChartAccountLedger chartAccountLedger;

    private Integer createdBy;
    private Integer updatedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getLedger() {
        return isLedger;
    }

    public void setLedger(Boolean ledger) {
        isLedger = ledger;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Short getCaLevel() {
        return caLevel;
    }

    public void setCaLevel(Short caLevel) {
        this.caLevel = caLevel;
    }

    public ChartAccount getParent() {
        return parent;
    }

    public void setParent(ChartAccount parent) {
        this.parent = parent;
    }

    public Set<ChartAccount> getChildren() {
        return children;
    }

    public void setChildren(Set<ChartAccount> children) {
        this.children = children;
    }

    public ChartAccountType getChartAccountType() {
        return chartAccountType;
    }

    public void setChartAccountType(ChartAccountType chartAccountType) {
        this.chartAccountType = chartAccountType;
    }

    public ChartAccountGroup getChartAccountGroup() {
        return chartAccountGroup;
    }

    public void setChartAccountGroups(ChartAccountGroup chartAccountGroup) {
        this.chartAccountGroup = chartAccountGroup;
    }

    public Set<AutoVoucherMap> getCrHeadMaps() {
        return crHeadMaps;
    }

    public void setCrHeadMaps(Set<AutoVoucherMap> crHeadMaps) {
        this.crHeadMaps = crHeadMaps;
    }

    public Set<AutoVoucherMap> getDrHeadMaps() {
        return drHeadMaps;
    }

    public void setDrHeadMaps(Set<AutoVoucherMap> drHeadMaps) {
        this.drHeadMaps = drHeadMaps;
    }

    public ChartAccountLedger getChartAccountLedger() {
        return chartAccountLedger;
    }

    public void setChartAccountLedger(ChartAccountLedger chartAccountLedger) {
        this.chartAccountLedger = chartAccountLedger;
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
