package com.adex.customerapi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

    private final String BASE_URL = "http://localhost:7070/v1/resource/accept";
    private final WebClient webClient = WebClient.builder().baseUrl(BASE_URL).build();

    public AcceptResponse passJson(String body) {
        return webClient.post().bodyValue(body)
                .retrieve().bodyToMono(AcceptResponse.class).share().block();//share runs in another thread
    }
}


