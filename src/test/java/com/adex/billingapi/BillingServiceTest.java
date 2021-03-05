package com.adex.billingapi;

import com.adex.customerapi.Customer;
import com.adex.customerapi.StatsService;
import com.adex.customerapi.data.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BillingServiceTest {

    @Autowired
    BillingService billingService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    StatsService statsService;

    @Test
    public void findByCustomerIdAndDate_listIsNotEmpty(){
        final Customer customer = new Customer();
        customer.setName("Client89");
        customer.setActive(true);
        customerRepository.save(customer);

        final Instant clientTime = Instant.now();
        statsService.addValid(customer, clientTime);
        statsService.addInvalid(customer, clientTime);

        Instant date = Instant.now();

        BillingResponse billingResponse = billingService.findByCustomerIdAndDate(customer.getId(), date);
        assertThat(billingResponse).isNotNull();
        assertThat(billingResponse.getTotalRequestCount()).isEqualTo(2);
        assertThat(billingResponse.getHourlyStatsList()).isNotEmpty();
        assertThat(billingResponse.getHourlyStatsList()).hasSize(1);

        customerRepository.delete(customer);

    }

    @Test
    public void findByCustomerIdAndDate_listIsEmpty(){
        final Customer customer = new Customer();
        customer.setName("Client90");
        customer.setActive(true);
        customerRepository.save(customer);

        Instant date = Instant.now();

        BillingResponse billingResponse = billingService.findByCustomerIdAndDate(customer.getId(), date);
        assertThat(billingResponse).isNotNull();
        assertThat(billingResponse.getTotalRequestCount()).isEqualTo(0);
        assertThat(billingResponse.getHourlyStatsList()).isEmpty();
        assertThat(billingResponse.getHourlyStatsList()).hasSize(0);

        customerRepository.delete(customer);
    }
}