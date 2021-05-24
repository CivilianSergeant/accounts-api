package technology.grameen.gaccounting.accounting.entity;

import javax.persistence.*;

@Entity
@Table(name = "general_settings")
public class GeneralSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String alias;

    private String value;


}
