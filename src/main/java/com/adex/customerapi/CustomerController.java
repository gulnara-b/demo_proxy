package com.adex.customerapi;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final MainFilterService mainFilterService;

  @SneakyThrows
  @PostMapping("/request")
  public Mono<AcceptResponse> customerRequest(
          @RequestHeader(value = "User-Agent") String userAgent,
          @RequestBody String body) {
    return Mono.fromCallable(() -> mainFilterService.mainFilter(body, userAgent));
  }
}
