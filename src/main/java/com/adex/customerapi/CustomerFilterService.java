package com.adex.customerapi;


import com.adex.customerapi.data.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerFilterService {

    static final String CUSTOMER_DOES_NOT_EXIST_OR_NOT_ACTIVE = "Error! This customer doesn't exist in our DB or is not active";

    private final CustomerRepository customerRepository;

    public Optional<Customer> customerRequestFilter(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            if (customer.get().isActive()) {
                return customer;
            }
            }
        return Optional.empty();
        }
    }


