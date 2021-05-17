package technology.grameen.gaccounting.projection.authserver;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface VoucherList {

    interface VoucherType {
        Long getId();
        String getName();
    }

    Long getId();
    LocalDateTime getVoucherDate();
    String getVoucherNo();
    String getVoucherMode();
    VoucherType getVoucherType();
    BigDecimal getTotalCreditAmount();
    BigDecimal getTotalDebitAmount();
    LocalDateTime getCreatedAt();
}
