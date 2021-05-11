package technology.grameen.gaccounting.configs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "healthEntityManagerFactory",
        transactionManagerRef = "healthTransactionManager",
        basePackages = {"technology.grameen.gaccounting.gkhealth.repositories"}
)
public class HealthDbManager {

    @Autowired
    Environment env;

    @Bean(name = "gkHealthDataSource")
    @ConfigurationProperties(prefix = "spring.gkhealth.datasource")
    public DataSource dataSource(){

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.gkhealth.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.gkhealth.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.gkhealth.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.gkhealth.datasource.password"));
        return dataSource;
    }

    @Bean(name = "healthEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            @Qualifier("gkHealthDataSource")DataSource dataSource){

        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.dialect","org.hibernate.dialect.Oracle12cDialect");
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("technology.grameen.gaccounting.gkhealth.entity")
                .persistenceUnit("HealthCenter")
                .build();

    }

    @Bean(name = "healthTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("healthEntityManagerFactory")EntityManagerFactory entityManagerFactory
    ){
        return  new JpaTransactionManager(entityManagerFactory);
    }
}
