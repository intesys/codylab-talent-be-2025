package it.intesys.codylab.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${app.datasource:h2}")
    private String dbType;

    @Value("${spring.datasource.url:}")
    private String url;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Bean
    public DataSource dataSource() {
        if ("postgres".equalsIgnoreCase(dbType)) {
            return DataSourceBuilder.create()
                    .url(url)
                    .username(username)
                    .password(password)
                    .driverClassName("org.postgresql.Driver")
                    .build();
        } else if ("h2".equalsIgnoreCase(dbType)) {
            return DataSourceBuilder.create()
                    .url("jdbc:h2:mem:testdb")
                    .username("sa")
                    .password("")
                    .driverClassName("org.h2.Driver")
                    .build();
        } else {
            throw new IllegalArgumentException("Tipo DB non supportato: " + dbType);
        }
    }
}
