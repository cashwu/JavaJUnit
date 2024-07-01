package com.cashwu.javajunit.ch19_spring_jdbc;

import com.cashwu.javajunit.ch19_spring_jdbc.dao.CountryDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author cash.wu
 * @since 2024/07/01
 */
@Configuration
public class AppConfig {


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:ch19_spring_jdbc/db-schema.sql")
                .build();
    }

    @Bean
    public CountryDao countryDao() {
        CountryDao countryDao = new CountryDao();
        countryDao.setDataSource(dataSource());
        return countryDao;
    }

    @Bean
    public CountriesLoader countriesLoader() {
        CountriesLoader countriesLoader = new CountriesLoader();
        countriesLoader.setDataSource(dataSource());
        return countriesLoader;
    }
}
