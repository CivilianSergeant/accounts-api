package technology.grameen.gaccounting.projection.authserver;

public interface LedgerAccountList {
    Long getId();
    String getTypeName();
    String getCtCode();
    String getTitle();
    Long getParentId();
    String getParent();
    String getCaCode();
    Integer getIsLedger();
    String getContactAddress();
    String getContactName();
    String getContactEmail();
    String getContactPhone();
}
