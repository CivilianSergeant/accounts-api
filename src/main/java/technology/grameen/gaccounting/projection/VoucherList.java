package technology.grameen.gaccounting.projection;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface VoucherList {

    interface VoucherType {
        Long getId();
        String getName();
    }

    Long getId();

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime getVoucherDate();
    String getVoucherNo();
    String getVoucherMode();
    VoucherType getVoucherType();
    BigDecimal getTotalCreditAmount();
    BigDecimal getTotalDebitAmount();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getCreatedAt();
}
