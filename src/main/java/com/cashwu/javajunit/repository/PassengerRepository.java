package com.cashwu.javajunit.repository;

import com.cashwu.javajunit.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
