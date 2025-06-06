package it.intesys.codylab.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.intesys.codylab.config.CodyLabDatasourceProperties;

import javax.sql.DataSource;

public class DataSourceFactory {

    public static DataSource makeDataSource(CodyLabDatasourceProperties properties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getUrl());
        hikariConfig.setUsername(properties.getUsername());
        hikariConfig.setPassword(properties.getPassword());
        hikariConfig.setDriverClassName(properties.getDriver());
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(hikariConfig);

    }

    public static DataSource makeDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariConfig.setUsername("codylab");
        hikariConfig.setPassword("cody|_ab2025");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(hikariConfig);
    }
}
