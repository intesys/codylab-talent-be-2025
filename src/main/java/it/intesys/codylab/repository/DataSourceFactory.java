package it.intesys.codylab.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    public static DataSource makeDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        // hikariConfig.setJdbcUrl("jdbc:h2:~/codylab-2025;INIT=RUNSCRIPT FROM 'src/main/resources/codylab.sql';");
        hikariConfig.setJdbcUrl("jdbc:h2:~/codylab-2025");
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("password");
        hikariConfig.setDriverClassName("org.h2.Driver");
        return new HikariDataSource(hikariConfig);
    }
}
