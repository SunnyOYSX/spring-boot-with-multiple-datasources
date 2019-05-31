package io.ifelsecoders.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "carsEntityManagerFactory",
        transactionManagerRef = "carsTransactionManager",
        basePackages = "io.ifelsecoders.cars.repository")
public class CarsDatasourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties carsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "carsDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource carsDataSource() {
        return carsDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "carsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean carsEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("commonJpaProperties")
                    Map<String, String> jpaProperties) {
        return builder
                .dataSource(carsDataSource())
                .packages("io.ifelsecoders.cars.entity")
                .persistenceUnit("cars-db")
                .properties(jpaProperties)
                .build();
    }

    @Primary
    @Bean(name = "carsTransactionManager")
    public PlatformTransactionManager carsTransactionManager(
            @Qualifier("carsEntityManagerFactory") EntityManagerFactory carsEntityManagerFactory) {
        return new JpaTransactionManager(carsEntityManagerFactory);
    }
}
