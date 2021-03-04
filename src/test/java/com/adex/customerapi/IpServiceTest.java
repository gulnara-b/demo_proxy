package com.adex.customerapi;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class IpServiceTest {

  @Autowired
  IpService ipService;

  @SneakyThrows
  @Test
  public void ipAllowed() {

    boolean ipAllowed = ipService.isAllowed("10.0.0.1");
    assertTrue(ipAllowed, "Random IP should be allowed");
  }

  @SneakyThrows
  @Test
  public void ipNotAllowed() {
    long ipLong = 2130706433;//val from the sql init script
    String ipString = Converters.ipFromLong(ipLong);
    boolean ipAllowed = ipService.isAllowed(ipString);
    assertFalse(ipAllowed, "IP should not be not allowed");
  }

}