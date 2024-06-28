package com.cashwu.javajunit.ch17;

import com.cashwu.javajunit.model.Country;
import com.cashwu.javajunit.model.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
@TestConfiguration
public class TestBeans {

    @Bean
    Passenger createPassenger() {
        Passenger passenger = new Passenger("John Smith");
        passenger.setCountry(createCountry());
        passenger.setIsRegistered(false);
        return passenger;
    }

    @Bean
    Country createCountry() {
        return new Country("UAS", "US");
    }
}
