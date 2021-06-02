package technology.grameen.gaccounting.services.report;

import java.math.BigDecimal;

public class LedgerAccount {
    Long id;
    String ledgerAcc;
    String transType;
    BigDecimal openingBalance;
    BigDecimal debit;
    BigDecimal credit;

    public LedgerAccount(Long id, String ledgerAcc, String transType, BigDecimal openingBalance, BigDecimal debit, BigDecimal credit) {
        this.id = id;
        this.ledgerAcc = ledgerAcc;
        this.transType = transType;
        this.openingBalance = openingBalance;
        this.debit = debit;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public String getLedgerAcc() {
        return ledgerAcc;
    }

//    public String getTransType() {
//        return transType;
//    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }
}
