package technology.grameen.gaccounting.projection;

public interface OpeningBalanceDiff {

    Double getTotalOpeningDr();

    Double getTotalOpeningCr();

    Double getDifferenceAmount();

    String getBalanceType();
}
