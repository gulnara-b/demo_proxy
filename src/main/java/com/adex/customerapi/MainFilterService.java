package com.adex.customerapi;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MainFilterService {

    private static final ObjectMapper objectMapper = Converters.buildObjectMapper();
    private final CustomerFilterService customerFilterService;
    private final IpService ipService;
    private final UaService uaService;
    private final StatsService statsService;
    private final WebClientService passService;

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @SneakyThrows
    public AcceptResponse mainFilter(String body, String ua) {
        CustomerRequestModel customerRequestModel;
        int customerIdFromJson;
        Instant timestampFromJson;
        try {
            JsonNode jsonNode = objectMapper.readTree(body);
            customerIdFromJson = Integer.parseInt(jsonNode.get("customerID").asText());
          timestampFromJson = Instant.ofEpochSecond(Long.parseLong(jsonNode.get("timestamp").asText()));
        } catch (Exception je) {
            return new AcceptResponse(false, je.getMessage());
        }
        final Optional<Customer> customer = customerFilterService.customerRequestFilter(customerIdFromJson);

        if (!customer.isPresent()) {
            return new AcceptResponse(false, CustomerFilterService.CUSTOMER_DOES_NOT_EXIST_OR_NOT_ACTIVE);
        } else {
            try {
            customerRequestModel = objectMapper.readValue(body, CustomerRequestModel.class);
        } catch (Exception ex) {
            statsService.addInvalid(customer.get(), timestampFromJson);// TODO
            return new AcceptResponse(false, ex.getMessage());
        }
        Set<ConstraintViolation<CustomerRequestModel>> fieldErrors = validator.validate(customerRequestModel);
                if (fieldErrors.size() > 0) {
                    statsService.addInvalid(customer.get(), customerRequestModel.timestamp);
                    return new AcceptResponse(false, fieldErrors.toString());
                } else {
                String ip = customerRequestModel.remoteIP;
                if (!ipService.isAllowed(ip)) {
                    statsService.addInvalid(customer.get(), customerRequestModel.timestamp);
                    return new AcceptResponse(false, "IP address is not allowed");
                } else {
                    if (!uaService.isAllowed(ua)) {
                        statsService.addInvalid(customer.get(), customerRequestModel.timestamp);
                        return new AcceptResponse(false, "User-agent is not allowed");
                    }
                }
            }
                statsService.addValid(customer.get(), customerRequestModel.timestamp);
                return passService.passJson(body);
            }
        }
}

