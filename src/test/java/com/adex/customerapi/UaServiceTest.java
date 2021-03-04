package com.adex.customerapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UaServiceTest {

  @Autowired
  UaService uaService;

  @Test
  public void uaAllowed() {

    boolean uaAllowed = uaService.isAllowed("User123");
    assertTrue(uaAllowed, "Random UA should be allowed");
  }

  @Test
  public void ipNotAllowed() {
    String ua = "Googlebot";//ua from the sql init script
    boolean uaAllowed = uaService.isAllowed(ua);
    assertFalse(uaAllowed, "UA should not be allowed");
  }
}