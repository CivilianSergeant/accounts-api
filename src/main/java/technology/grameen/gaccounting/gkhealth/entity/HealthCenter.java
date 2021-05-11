package technology.grameen.gaccounting.gkhealth.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "health_centers")
public class HealthCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long apiOfficeId;
    private String name;

    private String centerCode;
    private String address;

    private Boolean isActive;


    private Integer officeLevel;

    private String firstLevel;

    private String secondLevel;

    private String thirdLevel;

    private String fourthLevel;

    private String fifthLevel;

    private Integer officeTypeId;



    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApiOfficeId() {
        return apiOfficeId;
    }

    public void setApiOfficeId(Long apiOfficeId) {
        this.apiOfficeId = apiOfficeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getOfficeLevel() {
        return officeLevel;
    }

    public void setOfficeLevel(Integer officeLevel) {
        this.officeLevel = officeLevel;
    }

    public String getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(String firstLevel) {
        this.firstLevel = firstLevel;
    }

    public String getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(String secondLevel) {
        this.secondLevel = secondLevel;
    }

    public String getThirdLevel() {
        return thirdLevel;
    }

    public void setThirdLevel(String thirdLevel) {
        this.thirdLevel = thirdLevel;
    }

    public String getFourthLevel() {
        return fourthLevel;
    }

    public void setFourthLevel(String fourthLevel) {
        this.fourthLevel = fourthLevel;
    }

    public String getFifthLevel() {
        return fifthLevel;
    }

    public void setFifthLevel(String fifthLevel) {
        this.fifthLevel = fifthLevel;
    }

    public Integer getOfficeTypeId() {
        return officeTypeId;
    }

    public void setOfficeTypeId(Integer officeTypeId) {
        this.officeTypeId = officeTypeId;
    }
}
