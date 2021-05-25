package technology.grameen.gaccounting.accounting.entity;

import javax.persistence.*;

@Entity
@Table(name = "ca_groups")
public class ChartAccountGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private ChartAccount chartAccount;

    public void setId(Long id) {
        this.id = id;
    }

    public void setChartAccount(ChartAccount chartAccount) {
        this.chartAccount = chartAccount;
    }
}
