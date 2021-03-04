package com.adex.customerapi;

import com.adex.customerapi.data.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

@SpringBootTest
class MainFilterServiceTest {

  public static final String UA = "GenericBrowser";
  @Autowired
  MainFilterService mainFilterService;
  @Autowired
  CustomerRepository customerRepository;

  private static final ObjectMapper objectMapper = Converters.buildObjectMapper();

  @Test
  public void mainFilterSuccess() throws JsonProcessingException {
    final Customer customer = new Customer();
    customer.setName("Client25");
    customer.setActive(true);
    customerRepository.save(customer);
    final CustomerRequestModel crm = new CustomerRequestModel(customer.getId(), 2, "aaaaaaaa-bbbb-cccc-1111-222222222222",
            "127.0.0.11", Instant.ofEpochMilli(1600000000));
    String body = objectMapper.writeValueAsString(crm);

    AcceptResponse acceptResponse = mainFilterService.mainFilter(body, UA);
    assertThat(acceptResponse).isNotNull();
    assertThat(acceptResponse.isAccepted()).isTrue();
    customerRepository.delete(customer);

  }

  @Test
  public void mainFilterByIp() throws JsonProcessingException {
    final Customer customer = new Customer();
    customer.setName("Client25");
    customer.setActive(true);
    customerRepository.save(customer);
    final CustomerRequestModel crm = new CustomerRequestModel(customer.getId(), 2, "aaaaaaaa-bbbb-cccc-1111-222222222222",
            "127.0.0.1", Instant.ofEpochMilli(1600000000));
    String body = objectMapper.writeValueAsString(crm);

    AcceptResponse acceptResponse = mainFilterService.mainFilter(body, UA);
    assertThat(acceptResponse).isNotNull();
    assertThat(acceptResponse.isAccepted()).isFalse();
    customerRepository.delete(customer);

  }

  @Test
  public void mainFilterByUserAgent() throws JsonProcessingException {
    final Customer customer = new Customer();
    customer.setName("Client25");
    customer.setActive(true);
    customerRepository.save(customer);
    final CustomerRequestModel crm = new CustomerRequestModel(customer.getId(), 2, "aaaaaaaa-bbbb-cccc-1111-222222222222",
            "127.0.0.1", Instant.ofEpochMilli(1600000000));
    String body = objectMapper.writeValueAsString(crm);

    AcceptResponse acceptResponse = mainFilterService.mainFilter(body, "Googlebot");
    assertThat(acceptResponse).isNotNull();
    assertThat(acceptResponse.isAccepted()).isFalse();
    customerRepository.delete(customer);

  }

  @Test
  public void mainFilterByCustomer() throws JsonProcessingException {
    final CustomerRequestModel crm = new CustomerRequestModel(-1, 2, "aaaaaaaa-bbbb-cccc-1111-222222222222",
            "127.0.0.1", Instant.ofEpochMilli(1600000000));
    String body = objectMapper.writeValueAsString(crm);

    AcceptResponse acceptResponse = mainFilterService.mainFilter(body, UA);
    assertThat(acceptResponse).isNotNull();
    assertThat(acceptResponse.isAccepted()).isFalse();
  }

  @Test
  public void mainFilterByUserId() throws JsonProcessingException {
    final Customer customer = new Customer();
    customer.setName("Client25");
    customer.setActive(true);
    customerRepository.save(customer);
    final CustomerRequestModel crm = new CustomerRequestModel(customer.getId(), 2, "fakeUUID",
            "127.0.0.1", Instant.ofEpochMilli(1600000000));
    String body = objectMapper.writeValueAsString(crm);

    AcceptResponse acceptResponse = mainFilterService.mainFilter(body, UA);
    assertThat(acceptResponse).isNotNull();
    assertThat(acceptResponse.isAccepted()).isFalse();
    customerRepository.delete(customer);

  }

}