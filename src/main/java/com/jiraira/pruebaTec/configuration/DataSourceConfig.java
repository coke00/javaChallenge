package com.jiraira.pruebaTec.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${environment.datasource.url}")
    private String dataSourceUrl;
    @Value("${environment.datasource.username}")
    private String dataSourceUsername;
    @Value("${environment.datasource.password}")
    private String dataSourcePassword;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure().dataSource(dataSource()).load();
        flyway.migrate();
        return flyway;
    }
}
