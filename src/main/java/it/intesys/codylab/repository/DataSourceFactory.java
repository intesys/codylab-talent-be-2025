package it.intesys.codylab.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {

    private static HikariDataSource dataSource;

    public static DataSource makeDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
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
        return dataSource;
    }

    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}

