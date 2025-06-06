package it.intesys.codylab.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    public static DataSource makeDataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");


        hikariConfig.setUsername("codylab");


        hikariConfig.setPassword("cody|_ab2025");


        hikariConfig.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(hikariConfig);
    }
}
