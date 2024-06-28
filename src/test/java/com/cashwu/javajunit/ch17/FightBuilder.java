package com.cashwu.javajunit.ch17;

import com.cashwu.javajunit.model.Country;
import com.cashwu.javajunit.model.Flight;
import com.cashwu.javajunit.model.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
@TestConfiguration
public class FightBuilder {

    private static Map<String, Country> countriesMap = new HashMap<>();

    static {
        countriesMap.put("AU", new Country("Australia", "AU"));
        countriesMap.put("US", new Country("UAS", "US"));
        countriesMap.put("UK", new Country("United Kingdom", "UK"));
    }

    @Bean
    Flight buildFightFromCsv() throws IOException {
        Flight flight = new Flight("AA!234", 20);

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/test/resources/flights_information.csv"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Passenger passenger = createPassengerFromString(line);
                flight.addPassenger(passenger);
            }
        }
        return flight;
    }

    private Passenger createPassengerFromString(String passengerData) {
        String[] passengerInfo = passengerData.split(";");

        Passenger passenger = new Passenger(passengerInfo[0].trim());
        passenger.setCountry(countriesMap.get(passengerInfo[1].trim()));
        passenger.setIsRegistered(false);

        return passenger;
    }
}
