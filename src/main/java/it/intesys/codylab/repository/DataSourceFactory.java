package it.intesys.codylab.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    public static HikariDataSource dataSource;

    public static DataSource makeDataSource(int scelta) {
        HikariConfig config = new HikariConfig();

        if (scelta == 1) {
            config.setJdbcUrl("jdbc:h2:~/codylab-2025;AUTO_SERVER=TRUE");
            config.setUsername("sa");
            config.setPassword("password");
            config.setDriverClassName("org.h2.Driver");
            dataSource = new HikariDataSource(config);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (!dataSource.isClosed()) {
                    dataSource.close();
                }
            }));
        }
        else if (scelta == 2) {
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
            config.setUsername("gaetano");
            config.setPassword("password");
            config.setDriverClassName("org.postgresql.Driver");
            dataSource = new HikariDataSource(config);
        }
        return new HikariDataSource(config);
    }

    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}

