package technology.grameen.gaccounting.accounting.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChartAccount chartAccount;

    @ManyToOne
    private Voucher voucher;

    private BigDecimal amount;

    @Column(length = 2)
    private String transactionType;

    private String narration;

    private BigDecimal balance;

    private Integer officeId;

    private Short officeTypeId;

    private String realm;
}
