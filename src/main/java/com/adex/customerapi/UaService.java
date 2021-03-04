package com.adex.customerapi;

import com.adex.customerapi.data.UaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UaService {

    private final UaRepository uaRepository;

    @SneakyThrows
    public Boolean isAllowed(String ua) {
        return !uaRepository.findById(ua).isPresent();
    }
}