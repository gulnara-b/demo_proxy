package com.adex.customerapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebClientServiceTest {
    @Autowired
    WebClientService webClientService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void passJson_success() throws JsonProcessingException {
        CustomerRequestModel customerRequestModel = new CustomerRequestModel(1, 2, "aaaaaaaa-bbbb-cccc-1111-222227678999",
                "127.0.0.11", Instant.ofEpochMilli(1600000000));
        AcceptResponse acceptResponse = webClientService.passJson(objectMapper.writeValueAsString(customerRequestModel));
        assertThat(acceptResponse).isNotNull();
        assertThat(acceptResponse.isAccepted()).isTrue();
    }
    }