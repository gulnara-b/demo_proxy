package com.adex.customerapi.data;

import com.adex.customerapi.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
