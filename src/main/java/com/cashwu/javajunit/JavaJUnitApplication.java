package com.cashwu.javajunit;

import com.cashwu.javajunit.builder.FlightBuilder;
import com.cashwu.javajunit.model.Country;
import com.cashwu.javajunit.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@Import(FlightBuilder.class)
@SpringBootApplication
public class JavaJUnitApplication {

    @Autowired
    private Map<String, Country> countriesMap;

    public static void main(String[] args) {
        SpringApplication.run(JavaJUnitApplication.class, args);
    }

    @Bean
    CommandLineRunner configureRepository(CountryRepository countryRepository) {
        return args -> {
            countryRepository.saveAll(countriesMap.values());
        };
    }

}
