package com.cashwu.javajunit.builder;

import com.cashwu.javajunit.model.Country;
import com.cashwu.javajunit.model.Flight;
import com.cashwu.javajunit.model.Passenger;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
public class FlightBuilder {

    private static Map<String, Country> countriesMap = new HashMap<>();

    public FlightBuilder() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/countries_information.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] countriesString = line.split(";");
                Country country = new Country(countriesString[0].trim(), countriesString[1].trim());
                countriesMap.put(countriesString[1].trim(), country);
            }
        }
    }

    @Bean
    Map<String, Country> getCountriesMap() {
        return Collections.unmodifiableMap(countriesMap);
    }

    @Bean
    Flight buildFightFromCsv() throws IOException {
        Flight flight = new Flight("AA!234", 20);

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/flights_information.csv"))) {
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
