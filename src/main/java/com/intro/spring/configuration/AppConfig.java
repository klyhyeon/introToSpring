package com.intro.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean(name="dataSource")
    @Profile("development")
    DataSource dataSourceForDevelopment() {
        return null;
    }

    @Bean(name="dataSource")
    @Profile("staging")
    DataSource dataSourceForStaging() {
        return null;
    }

}
