package com.cashwu.javajunit.ch21;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author cash.wu
 * @since 2024/07/03
 */
public class BonusPolicy {

    @Given("we have a regular passenger with a mileage")
    public void we_have_a_regular_passenger_with_a_mileage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the regular passenger travels {int} and {int} and {int}")
    public void the_regular_passenger_travels_and_and(Integer int1,
                                                      Integer int2,
                                                      Integer int3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the bonus points of the regular passenger should be {int}")
    public void the_bonus_points_of_the_regular_passenger_should_be(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
