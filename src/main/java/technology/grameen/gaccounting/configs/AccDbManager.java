package technology.grameen.gaccounting.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"technology.grameen.gaccounting.accounting.repositories"}
        )
public class AccDbManager {

    @Autowired
    Environment env;

    @Primary
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.accounting.datasource")
    public DataSource datasource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.accounting.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.accounting.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.accounting.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.accounting.datasource.password"));
        return dataSource;

    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("datasource")DataSource datasource){
        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.dialect","org.hibernate.dialect.Oracle12cDialect");
        properties.put("hibernate.hbm2ddl.auto","update");
        return builder.dataSource(datasource)
                .properties(properties)
                .packages("technology.grameen.gaccounting.accounting.entity")
                .persistenceUnit("Menu")
                .persistenceUnit("MenuPermission")
                .persistenceUnit("ChartAccountType")
                .persistenceUnit("ChartAccount")
                .persistenceUnit("ChartAccountGroup")
                .persistenceUnit("ChartAccountLedger")
                .persistenceUnit("AccMap")
                .persistenceUnit("AutoVoucherMap")
                .persistenceUnit("VoucherType")
                .persistenceUnit("Voucher")
                .persistenceUnit("Transaction")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory")EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }


}
