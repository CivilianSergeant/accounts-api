package technology.grameen.gaccounting.accounting.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private VoucherType voucherType;

    @Column(length = 100)
    private String voucherNo;

    private LocalDateTime voucherDate;
    private String voucherMode;

    @Column(length = 500)
    private String note;

    private BigDecimal totalDebitAmount;

    private BigDecimal totalCreditAmount;

    private Boolean status;

    private Integer createdBy;
    private Integer updatedBy;

    private Integer officeId;

    private Short officeTypeId;

    private String realm;

    @OneToMany(mappedBy = "voucher")
    private Set<Transaction> transactions;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
