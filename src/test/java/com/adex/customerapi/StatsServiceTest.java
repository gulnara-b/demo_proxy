package com.adex.customerapi;

import com.adex.customerapi.data.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StatsServiceTest {
    @Autowired
    StatsService statsService;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void addAndUpdateValidStats() {
        final Customer customer = new Customer();
        customer.setName("Client25");
        customer.setActive(true);
        customerRepository.save(customer);

        assertThat(customer.getId()).isGreaterThan(0);

        final Instant clientTime = Instant.now().minusSeconds(new Random().nextInt(10));
        final HourlyStats hourlyStats = statsService.addValid(customer, clientTime);
        assertThat(hourlyStats.getId()).isNotNull();
        assertThat(hourlyStats.getValidCount()).isEqualTo(1L);
        assertThat(hourlyStats.getInvalidCount()).isEqualTo(0L);

        final HourlyStats updatedStats = statsService.addValid(customer, clientTime);
        assertThat(updatedStats.getId()).isEqualTo(hourlyStats.getId());
        assertThat(updatedStats.getValidCount()).isEqualTo(2L);
        assertThat(updatedStats.getInvalidCount()).isEqualTo(0L);

        final HourlyStats updatedStats1 = statsService.addInvalid(customer, clientTime);
        assertThat(updatedStats1.getId()).isEqualTo(hourlyStats.getId());
        assertThat(updatedStats1.getValidCount()).isEqualTo(2L);
        assertThat(updatedStats1.getInvalidCount()).isEqualTo(1L);

        customerRepository.delete(customer);

    }
}