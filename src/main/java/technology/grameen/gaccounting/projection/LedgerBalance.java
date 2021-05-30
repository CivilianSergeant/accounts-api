package technology.grameen.gaccounting.projection;

import java.math.BigDecimal;

public interface LedgerBalance {
    Long getID();
    String getTitle();
    String getAlias();
    BigDecimal getBalance();
    BigDecimal getDebit();
    BigDecimal getCredit();
}
