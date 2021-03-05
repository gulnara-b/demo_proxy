package com.adex.customerapi;

import com.adex.customerapi.data.HourlyStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final HourlyStatsRepository hourlyStatsRepository;

    public HourlyStats addValid(Customer customer, Instant clientTime) {
        return realAdd(customer, clientTime, true);
    }

    private HourlyStats realAdd(Customer customer, Instant clientTime, boolean valid) {
        Optional<HourlyStats> hourlyStats = hourlyStatsRepository.findByCustomerIdAndTimestamp(customer.getId(),
                Converters.truncateMinutes(clientTime));

        HourlyStats currentStats;
        if (hourlyStats.isPresent()) {
            currentStats = hourlyStats.get();
        } else {
            HourlyStats newStats = new HourlyStats();
            newStats.setCustomer(customer);
            newStats.setTimestamp(clientTime);
            currentStats = newStats;
        }

        if (valid) {
            long validRequests = currentStats.getValidCount() + 1;
            currentStats.setValidCount(validRequests);
        } else {
            long invalidRequests = currentStats.getInvalidCount() + 1;
            currentStats.setInvalidCount(invalidRequests);
        }
        return hourlyStatsRepository.save(currentStats);
    }

    public HourlyStats addInvalid(Customer customer, Instant clientTime) {
        return realAdd(customer, clientTime, false);

    }
}
