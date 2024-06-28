package com.cashwu.javajunit.ch18;

import com.cashwu.javajunit.builder.FlightBuilder;
import com.cashwu.javajunit.exceptions.PassengerNotFoundException;
import com.cashwu.javajunit.model.Country;
import com.cashwu.javajunit.model.Flight;
import com.cashwu.javajunit.model.Passenger;
import com.cashwu.javajunit.repository.CountryRepository;
import com.cashwu.javajunit.repository.PassengerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(FlightBuilder.class)
public class RestApiTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Flight flight;

    @Autowired
    private Map<String, Country> countriesMap;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    void getAllCountry() throws Exception {
        when(countryRepository.findAll()).thenReturn(new ArrayList<>(countriesMap.values()));

        mvc.perform(get("/country")).andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$", hasSize(4)));

        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void getAllPassengers() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>(flight.getPassengers()));

        mvc.perform(get("/passenger")).andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$", hasSize(20)));

        verify(passengerRepository, times(1)).findAll();
    }

    @Test
    void passengerNotFound() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>(flight.getPassengers()));

        //        Throwable throwable = assertThrows(ServletException.class,
        //                                           () -> mvc.perform(get("/passenger/30"))
        //                                                    .andExpect(status().isNotFound()));
        //
        //        assertThat(throwable.getCause().getClass()).isEqualTo(PassengerNotFoundException.class);

        assertThatThrownBy(() -> mvc.perform(get("/passenger/30"))
                                    .andExpect(status().isNotFound())).isInstanceOf(
                ServletException.class).hasCauseInstanceOf(PassengerNotFoundException.class);
    }

    @Test
    void postPassenger() throws Exception {

        Passenger passenger = new Passenger("cash");
        passenger.setCountry(countriesMap.get("TW"));
        passenger.setIsRegistered(false);

        when(passengerRepository.save(passenger)).thenReturn(passenger);

        mvc.perform(post("/passenger").content(new ObjectMapper().writeValueAsString(passenger))
                                      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
           .andExpect(status().isCreated()).andExpect(jsonPath("$.name", is("cash")))
           .andExpect(jsonPath("$.country.codeName", is("TW")))
           .andExpect(jsonPath("$.country.name", is("Taiwan")))
           .andExpect(jsonPath("$.registered", is(Boolean.FALSE)));

        verify(passengerRepository, times(1)).save(passenger);

    }

    @Test
    void patchPassenger() throws Exception {

        Passenger passenger = new Passenger("cash123");
        passenger.setCountry(countriesMap.get("TW"));
        passenger.setIsRegistered(false);

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(passenger)).thenReturn(passenger);

        String update = """
                {
                    "name" : "cash",
                    "country": "AU",
                    "isRegistered" : "true"
                }
                """;

        mvc.perform(patch("/passenger/1").content(update).header(HttpHeaders.CONTENT_TYPE,
                                                                 MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());

        verify(passengerRepository, times(1)).findById(1L);
        verify(passengerRepository, times(1)).save(passenger);

    }

    @Test
    void deletePassenger() throws Exception {

        mvc.perform(delete("/passenger/4")).andExpect(status().isOk());

        verify(passengerRepository, times(1)).deleteById(4L);

    }

}