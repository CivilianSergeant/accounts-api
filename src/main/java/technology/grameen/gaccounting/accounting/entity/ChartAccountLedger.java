package technology.grameen.gaccounting.accounting.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ca_ledgers")
public class ChartAccountLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String contactName;

    @Column(length = 100)
    private String contactEmail;

    @Column(length = 100, nullable = false)
    private String contactPhone;

    @Column(length = 500, nullable = false)
    private String contactAddress;

    private Boolean isMaintainByBill;

    private Boolean isCostCenterApplicable;

    private Boolean isProvideBankDetail;

    private Boolean isRoundingMethod;

    private Integer roundingLimit;

    private BigDecimal currentBalance;

    private BigDecimal openingBalance;

    @ManyToOne
    private ChartAccount chartAccount;

}
