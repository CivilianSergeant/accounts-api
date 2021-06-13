package technology.grameen.gaccounting.projection;

public interface LedgerAccountList {
    Long getId();
    String getTypeName();
    String getCtCode();
    String getTitle();
    Long getParentId();
    String getParent();
    String getParentCode();
    String getCaCode();
    Integer getIsLedger();
    String getContactAddress();
    String getContactName();
    String getContactEmail();
    String getContactPhone();
}
