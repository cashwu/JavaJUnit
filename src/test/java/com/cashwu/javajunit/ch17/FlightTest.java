package com.cashwu.javajunit.ch17;

import com.cashwu.javajunit.model.Flight;
import com.cashwu.javajunit.model.Passenger;
import com.cashwu.javajunit.registration.PassengerRegistrationEvent;
import com.cashwu.javajunit.registration.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
@SpringBootTest
@Import(FightBuilder.class)
public class FlightTest {

    @Autowired
    private Flight flight;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testFlightPassengersRegistration() {

        flight.getPassengers().forEach(passenger -> {
            assertThat(passenger.isRegistered()).isFalse();
            registrationManager.getApplicationContext()
                               .publishEvent(new PassengerRegistrationEvent(passenger));
        });

        flight.getPassengers().forEach(passenger -> assertThat(passenger.isRegistered()).isTrue());
    }
}
