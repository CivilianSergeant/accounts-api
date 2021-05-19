package technology.grameen.gaccounting.projection.authserver;

public interface ChartAccountList {
    Long getId();
    String getTypeName();
    String getCtCode();
    String getTitle();
    String getCaCode();
    Integer getIsLedger();
}
