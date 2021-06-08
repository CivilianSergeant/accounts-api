package technology.grameen.gaccounting.projection;

import java.math.BigDecimal;

public interface LedgerDetail extends GroupDetail {

    interface ChartAccountLedger{
        Long getId();
        String getContactAddress();
        String getContactName();
        String getContactPhone();
        String getContactEmail();
        Boolean getMaintainByBill();
        Boolean getCostCenterApplicable();
        Boolean getProvideBankDetail();
        Boolean getRoundingMethod();
        Integer getRoundingLimit();
        BigDecimal getCurrentBalance();
        BigDecimal getOpeningBalance();
        BigDecimal getOpeningCreditBalance();
    }



    ChartAccountLedger getChartAccountLedger();
}
