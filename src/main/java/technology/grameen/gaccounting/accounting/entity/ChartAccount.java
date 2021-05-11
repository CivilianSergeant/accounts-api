package technology.grameen.gaccounting.accounting.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "chart_accounts")
public class ChartAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String code;
    private String description;
    private Integer sortOrder;
    private Boolean isLedger;
    private Boolean status;
    private Short caLevel;

    @ManyToOne
    private ChartAccount parent;

    @OneToMany(mappedBy = "parent")
    private Set<ChartAccount> children;

    @ManyToOne
    private ChartAccountType chartAccountType;

    @OneToMany(mappedBy = "chartAccount")
    private Set<ChartAccountGroup> chartAccountGroups;

    @OneToMany(mappedBy = "crHeader")
    private Set<AutoVoucherMap> crHeadMaps;

    @OneToMany(mappedBy = "drHeader")
    private Set<AutoVoucherMap> drHeadMaps;

    @OneToMany(mappedBy = "chartAccount")
    private Set<ChartAccountLedger> chartAccountLedgers;

    private Integer createdBy;
    private Integer updatedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
