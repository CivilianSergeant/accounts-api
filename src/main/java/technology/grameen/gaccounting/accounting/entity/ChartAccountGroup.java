package technology.grameen.gaccounting.accounting.entity;

import javax.persistence.*;

@Entity
@Table(name = "ca_groups")
public class ChartAccountGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChartAccount chartAccount;
}
