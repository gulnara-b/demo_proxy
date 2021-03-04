package com.adex.billingapi;

import com.adex.customerapi.Converters;
import com.adex.customerapi.HourlyStats;
import com.adex.customerapi.data.HourlyStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingService {

  private final HourlyStatsRepository hourlyStatsRepository;

  public BillingResponse findByCustomerIdAndDate(int customerId, Instant date) {
    Instant beginOfTheDay = Converters.truncateHours(date);
    Instant endOfTheDay = beginOfTheDay.plusSeconds(3600 * 24);
    List<HourlyStats> list = hourlyStatsRepository.findByCustomerIdAndTimestampBetween(customerId, beginOfTheDay, endOfTheDay);

    long totalRequestCount = list.stream().mapToLong(hs -> hs.getValidCount() + hs.getInvalidCount()).sum();
    return new BillingResponse(list, totalRequestCount);
  }
}