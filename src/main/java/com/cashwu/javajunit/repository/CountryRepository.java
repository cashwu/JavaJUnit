package com.cashwu.javajunit.repository;

import com.cashwu.javajunit.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cash.wu
 * @since 2024/06/28
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
