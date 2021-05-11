package technology.grameen.gaccounting.projection.authserver;

public interface VoucherTypeList {

    Integer getId();
    String getName();
    String getAlias();
    String getVoucherTypeCode();
    String getVoucherNumberType();
    String getNumberPrefix();
    String getDescription();
    String getModuleName();
    Integer getSortOrder();
    Boolean getStatus();
}
