package io.ifelsecoders.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "trucksEntityManagerFactory",
        transactionManagerRef = "trucksTransactionManager",
        basePackages = "io.ifelsecoders.trucks.repository")
public class TrucksDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("app.second.datasource")
    public DataSourceProperties truckDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "trucksDataSource")
    @ConfigurationProperties("app.second.datasource")
    public HikariDataSource trucksDataSource() {
        return truckDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "trucksEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean trucksEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("commonJpaProperties")
                    Map<String, String> jpaProperties) {
        return builder
                .dataSource(trucksDataSource())
                .packages("io.ifelsecoders.trucks.entity")
                .persistenceUnit("trucks-db")
                .properties(jpaProperties)
                .build();
    }

    @Bean(name = "trucksTransactionManager")
    public PlatformTransactionManager trucksTransactionManager(
            @Qualifier("trucksEntityManagerFactory") EntityManagerFactory trucksEntityManagerFactory) {
        return new JpaTransactionManager(trucksEntityManagerFactory);
    }
}
