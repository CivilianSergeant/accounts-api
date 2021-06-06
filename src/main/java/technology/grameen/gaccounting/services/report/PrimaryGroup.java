package technology.grameen.gaccounting.services.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PrimaryGroup {


    String title;
    BigDecimal debitAmount = BigDecimal.valueOf(0);
    BigDecimal creditAmount = BigDecimal.valueOf(0);
    BigDecimal opengingBalance = BigDecimal.valueOf(0);

    List<SubGroup> subGroups = new ArrayList<>();

    public PrimaryGroup( String title) {

        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubGroup> getSubGroups() {
        return subGroups;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getOpengingBalance() {
        return opengingBalance;
    }

    public void setOpengingBalance(BigDecimal opengingBalance) {
        this.opengingBalance = opengingBalance;
    }
}
