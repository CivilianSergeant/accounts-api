package technology.grameen.gaccounting.projection.authserver;

public interface GroupDetail {

    interface Parent{
        Long getId();
        String getTitle();
    }

    interface ChartAccountType{
        Integer getId();
        String getName();
    }

    Long getId();
    String getTitle();
    String getCode();
    String getCaLevel();
    Integer getSortOrder();
    Parent getParent();
    String getDescription();
    Boolean getStatus();
    Boolean getLedger();
    ChartAccountType getChartAccountType();
}
