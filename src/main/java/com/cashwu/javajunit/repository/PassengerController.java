package com.cashwu.javajunit.repository;

import com.cashwu.javajunit.exceptions.PassengerNotFoundException;
import com.cashwu.javajunit.model.Country;
import com.cashwu.javajunit.model.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
@RestController
public class PassengerController {

    private PassengerRepository repository;

    private final Map<String, Country> countriesMap;

    public PassengerController(PassengerRepository repository,
                               Map<String, Country> countriesMap) {
        this.repository = repository;
        this.countriesMap = countriesMap;
    }

    @GetMapping("/passenger")
    List<Passenger> findAll() {
        return repository.findAll();
    }

    @PostMapping("/passenger")
    @ResponseStatus(HttpStatus.CREATED)
    Passenger createPassenger(@RequestBody Passenger passenger) {
       return repository.save(passenger);
    }

    @GetMapping("/passenger/{id}")
    Passenger createPassenger(@PathVariable Long id) {
       return repository.findById(id)
               .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @PatchMapping("/passenger/{id}")
    Passenger createPassenger(@RequestBody Map<String, String> updates,
                              @PathVariable Long id) {

        return repository.findById(id).map(passenger -> {

            String name = updates.get("name");

            if (name != null) {
                passenger.setName(name);
            }

            Country country = countriesMap.get(updates.get("country"));

            if (country != null) {
                passenger.setCountry(country);
            }

            String isRegistered = updates.get("isRegistered");

            if (isRegistered != null) {
                //                        passenger.setIsRegistered(Boolean.parseBoolean(isRegistered));
                passenger.setIsRegistered(isRegistered.equalsIgnoreCase("true"));
            }

            return repository.save(passenger);
        }).orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @DeleteMapping("/passenger/{id}")
    void deletePassenger(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
