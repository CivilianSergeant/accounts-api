package technology.grameen.gaccounting.accounting.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auto_voucher_maps")
public class AutoVoucherMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String alias;
    private String moduleName;
    private String description;

    @ManyToOne
    private VoucherType voucherType;

    @ManyToOne
    private ChartAccount crHeader;

    private String crHeaderInfo;

    @ManyToOne
    private ChartAccount drHeader;

    private String drHeaderInfo;

    private Integer sortOrder;

    private Boolean status;

    private Integer createdBy;
    private Integer updatedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
