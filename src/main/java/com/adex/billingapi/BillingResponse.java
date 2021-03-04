package com.adex.billingapi;

import com.adex.customerapi.HourlyStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingResponse {
  private List<HourlyStats> hourlyStatsList;
  private long totalRequestCount;
}
