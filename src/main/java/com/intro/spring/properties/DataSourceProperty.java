package com.intro.spring.properties;

import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

public class DataSourceProperty {
    public static void main(String[] args) {
        DataSource dataSource(@Value("${datasource.driver-class-name}") String driverClassName) {
            dataSource.setDriverClassName(driverClassName);
        }

    }
}
