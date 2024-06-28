package com.cashwu.javajunit.exceptions;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
public class PassengerNotFoundException extends RuntimeException {

    public PassengerNotFoundException(Long id) {
        super("Passenger with id " + id + " not found");
    }
}
