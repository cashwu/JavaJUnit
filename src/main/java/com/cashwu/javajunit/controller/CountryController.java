package com.cashwu.javajunit.controller;

import com.cashwu.javajunit.model.Country;
import com.cashwu.javajunit.repository.CountryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
@RestController
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/country")
    List<Country> findAll() {
        return countryRepository.findAll();
    }
}
