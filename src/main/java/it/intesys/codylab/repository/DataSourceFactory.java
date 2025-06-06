package it.intesys.codylab.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.intesys.codylab.config.CodyLabDatasourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceFactory {

    public DataSource makedatasource(CodyLabDatasourceProperties properties) {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(properties.getUrl());


        hikariConfig.setUsername(properties.getUsername());


        hikariConfig.setPassword(properties.getPassword());


        hikariConfig.setDriverClassName(properties.getDriver());

        return new HikariDataSource(hikariConfig);
    }
    @Bean
    public static DataSource makeDataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");


        hikariConfig.setUsername("codylab");


        hikariConfig.setPassword("cody|_ab2025");


        hikariConfig.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(hikariConfig);
    }
}
