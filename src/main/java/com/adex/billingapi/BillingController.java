package com.adex.billingapi;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class BillingController {

  private final BillingService billingService;
  public static final DateFormat cf = new SimpleDateFormat("yyyy-MM-dd");

  @SneakyThrows
  @GetMapping("/customer/{customerId}/date/{dateStr}")
  public Mono<BillingResponse> billingReqById(@PathVariable int customerId, @PathVariable String dateStr) {
    Date date = cf.parse(dateStr);
    return Mono.fromCallable( () -> billingService.findByCustomerIdAndDate(customerId, date.toInstant()));
  }

}
