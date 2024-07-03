package com.cashwu.javajunit.ch21;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author cash.wu
 * @since 2024/07/03
 */
public class PassengersPolicy {

    private EconomyFlight economyFlight;
    private Passenger mike;

    @Given("there is an economy flight")
    public void there_is_an_economy_flight() {
        economyFlight = new EconomyFlight("1");
    }

    @When("we have a regular passenger")
    public void we_have_a_regular_passenger() {
        mike = new Passenger("Mike", false);
    }

    @Then("you can add and remove him from an economy flight")
    public void you_can_add_and_remove_him_from_an_economy_flight() {

        assertThat(economyFlight.getId()).isEqualTo("1");
        assertThat(economyFlight.addPassenger(mike)).isTrue();
        assertThat(economyFlight.getPassengersSet().size()).isEqualTo(1);

        assertThat(economyFlight.getPassengersSet()).contains(mike);
        assertThat(economyFlight.removePassenger(mike)).isTrue();
        assertThat(economyFlight.getPassengersSet().size()).isEqualTo(0);

        //        assertTrue(economyFlight.getPassengersSet().contains(mike));
    }

    @Then("you cannot add a regular passenger to an economy flight more than once")
    public void you_cannot_add_a_regular_passenger_to_an_economy_flight_more_than_once() {
        // Write code here that turns the phrase above into concrete actions
        //        throw new io.cucumber.java.PendingException();
    }
}
