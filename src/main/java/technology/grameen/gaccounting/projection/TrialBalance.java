package technology.grameen.gaccounting.projection;

import java.math.BigDecimal;
import java.util.Set;

public interface TrialBalance {

    String getAlias();
    String getPrimaryGroup();
    String getTransType();
    String getSubGroup();
    String getLedgerAcc();
    Long getId();
    BigDecimal getOpeningBalance();
    BigDecimal getDebit();
    BigDecimal getCredit();
}
