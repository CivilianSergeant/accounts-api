package technology.grameen.gaccounting.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface LedgerTransaction {
    Long getVid();
    Long getTid();
    String getChartType();
    String getAlias();
    LocalDateTime getTransactionDate();
    String getTransactionType();
    String getTitle();
    Double getAmount();

}
