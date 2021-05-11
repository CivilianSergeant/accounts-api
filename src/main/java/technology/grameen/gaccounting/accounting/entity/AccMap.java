package technology.grameen.gaccounting.accounting.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "acc_mappings")
public class AccMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private VoucherType voucherType;

    @Column(length = 2)
    private String trxType;

    private Boolean isRemoved;

    private String realm;

    private Integer createdBy;
    private Integer updatedBy;

    @ManyToOne
    private ChartAccount chartAccount;

    @ManyToOne
    private ChartAccount reverseAccChart;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
