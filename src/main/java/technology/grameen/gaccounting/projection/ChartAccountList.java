package technology.grameen.gaccounting.projection;

public interface ChartAccountList {
    Long getId();
    Integer getCaTypeId();
    String getTypeName();
    String getCtCode();
    String getTitle();
    Long getParentId();
    String getParent();
    String getCaCode();
    Integer getIsLedger();
}
