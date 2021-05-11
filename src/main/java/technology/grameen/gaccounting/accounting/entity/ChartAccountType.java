package technology.grameen.gaccounting.accounting.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ca_types")
public class ChartAccountType {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private String alias;
        private String code;
        private String description;
        private Boolean status;
        private Integer sortOrder;

        private Integer createdBy;
        private Integer updatedBy;

        @OneToMany(mappedBy = "chartAccountType")
        private Set<ChartAccount> chartAccounts;

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

        public Boolean getStatus() {
                return status;
        }

        public void setStatus(Boolean status) {
                this.status = status;
        }

        public Integer getSortOrder() {
                return sortOrder;
        }

        public void setSortOrder(Integer sortOrder) {
                this.sortOrder = sortOrder;
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

        public Set<ChartAccount> getChartAccounts() {
                return chartAccounts;
        }

        public void setChartAccounts(Set<ChartAccount> chartAccounts) {
                this.chartAccounts = chartAccounts;
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
