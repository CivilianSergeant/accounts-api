package technology.grameen.gaccounting.projection;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public interface VoucherDetail {

    interface VoucherType{
        Integer getId();
        String getAlias();
    }

    interface ChartAccount{
        Long getId();
        String getTitle();
    }

    interface Transaction{
        Long getId();
        BigDecimal getAmount();
        String getTransactionType();
        String getNarration();
        ChartAccount getChartAccount();
        String getRealm();
        Integer getOfficeId();
        Short getOfficeTypeId();
    }

    Long getId();
    VoucherType getVoucherType();
    String getVoucherNo();
    BigDecimal getTotalDebitAmount();
    BigDecimal getTotalCreditAmount();
    String getNote();
    Boolean getStatus();
    Integer getOfficeId();
    Short getOfficeTypeId();
    String getRealm();
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime getVoucherDate();
    Set<Transaction> getTransactions();
}
