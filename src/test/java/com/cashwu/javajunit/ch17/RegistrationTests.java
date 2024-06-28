package com.cashwu.javajunit.ch17;

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
@Import(TestBeans.class)
public class RegistrationTests {

    @Autowired
    private Passenger passenger;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testPersonRegistration() {

        registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));

        System.out.println("After registration");
        System.out.println(passenger);

        assertThat(passenger.isRegistered()).isTrue();
    }
}
