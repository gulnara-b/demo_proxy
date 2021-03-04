package com.adex.customerapi;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static java.time.Instant.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertersTest {


    @SneakyThrows
    @Test
    public void testTruncateMinutes() {
        final Instant time = now();
        final ZonedDateTime dateTime = time.atZone(ZoneOffset.UTC);

        final Instant parsedTime = Converters.truncateMinutes(time);
        final ZonedDateTime parsedDateTime = parsedTime.atZone(ZoneOffset.UTC);

        assertThat(parsedDateTime).isNotEqualTo(dateTime);//there should be a delta
        assertThat(parsedDateTime.getYear()).isEqualTo(dateTime.getYear());
        assertThat(parsedDateTime.getMonthValue()).isEqualTo(dateTime.getMonthValue());
        assertThat(parsedDateTime.getDayOfMonth()).isEqualTo(dateTime.getDayOfMonth());
        assertThat(parsedDateTime.getHour()).isEqualTo(dateTime.getHour());
    }

    @Test
    public void testIpConversions() {
        String privateIp = "10.90.1.1";
        String encodedIpd = Converters.ipFromLong(Converters.ipToLong(privateIp));
        assertEquals(privateIp, encodedIpd, "IPs should be equal");
    }
}