package com.cashwu.javajunit;

import com.cashwu.javajunit.builder.FlightBuilder;
import com.cashwu.javajunit.model.Country;
import com.cashwu.javajunit.model.Flight;
import com.cashwu.javajunit.repository.CountryRepository;
import com.cashwu.javajunit.repository.PassengerRepository;
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

    private final Map<String, Country> countriesMap;
    private final Flight flight;

    public JavaJUnitApplication(Map<String, Country> countriesMap,
                                Flight flight) {
        this.countriesMap = countriesMap;
        this.flight = flight;
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaJUnitApplication.class, args);
    }

    @Bean
    CommandLineRunner configureRepository(CountryRepository countryRepository,
                                          PassengerRepository passengerRepository) {
        return args -> {
            countryRepository.saveAll(countriesMap.values());
            passengerRepository.saveAll(flight.getPassengers());
        };
    }

}
