package technology.grameen.gaccounting.services.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SubGroup {

    String title;
    BigDecimal debit = BigDecimal.valueOf(0);
    BigDecimal credit = BigDecimal.valueOf(0);

    List<LedgerAccount> ledgerAccounts = new ArrayList<>();

    public SubGroup(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<LedgerAccount> getLedgerAccounts() {
        return ledgerAccounts;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
}
