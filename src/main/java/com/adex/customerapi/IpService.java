package com.adex.customerapi;

import com.adex.customerapi.data.IpRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IpService {

    private final IpRepository ipBlackRepository;

    @SneakyThrows
    public Boolean isAllowed(String  ipString) {
        long ipLong = Converters.ipToLong(ipString);
        return !ipBlackRepository.findById(ipLong).isPresent();
    }


}