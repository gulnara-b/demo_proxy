package com.adex.customerapi;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class ValidationTest {
  Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test public void validateModel(){
    CustomerRequestModel customerRequestModel = new CustomerRequestModel(
            -1, -1, "not-a-uuid", "fake", Instant.now().plusSeconds(1)
    );
    Set<ConstraintViolation<CustomerRequestModel>> fieldErrors = validator.validate(customerRequestModel);
    fieldErrors.forEach(v -> System.out.println("v = " + v));
    assertThat(fieldErrors).hasSize(5);

  }
  @Test public void validateGoodModel(){
    CustomerRequestModel customerRequestModel = new CustomerRequestModel(
            1, 1, UUID.randomUUID().toString(), "1.1.1.1", Instant.now().minusSeconds(1)
    );
    Set<ConstraintViolation<CustomerRequestModel>> violations = validator.validate(customerRequestModel);
    violations.forEach(v -> System.out.println("v = " + v));
    assertThat(violations).isEmpty();

  }
}
