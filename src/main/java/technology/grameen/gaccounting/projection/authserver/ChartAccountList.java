package technology.grameen.gaccounting.projection.authserver;

public interface ChartAccountList {
    Long getId();
    String getTypeName();
    String getCtCode();
    String getTitle();
    Long getParentId();
    String getParent();
    String getCaCode();
    Integer getIsLedger();
}
