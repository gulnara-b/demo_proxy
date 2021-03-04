package com.adex.customerapi;

import com.adex.customerapi.data.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerFilterServiceTest {

    @Autowired
    CustomerFilterService customerFilterService;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void customerRequestFilter_customerActive() {
        Optional<Customer> customer = customerRepository.findById(1);
        assertEquals(customer, customerFilterService.customerRequestFilter(1));
    }

    @Test
    public void customerRequestFilter_customerInactive() {
        assertEquals(Optional.empty(), customerFilterService.customerRequestFilter(3));
    }

    @Test
    public void customerRequestFilter_customerNotFound() {
       assertEquals(Optional.empty(), customerFilterService.customerRequestFilter(-1));
    }


}