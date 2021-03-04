package com.adex.customerapi.data;

import com.adex.customerapi.HourlyStats;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HourlyStatsRepository extends CrudRepository<HourlyStats, Integer> {
    Optional<HourlyStats> findByCustomerIdAndTimestamp(int customerId, Instant time);

    List<HourlyStats> findByCustomerIdAndTimestampBetween(int customerId, Instant from, Instant to);

    Optional<HourlyStats> findByCustomerId(int customerId);
}
