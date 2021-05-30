package technology.grameen.gaccounting.projection;

import java.math.BigDecimal;

public interface LedgerBalance {
    Long getID();
    String getTitle();
    BigDecimal getDebit();
    BigDecimal getCredit();
}
