package com.adex.customerapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public final class Converters {
    private static final DateFormat df = new SimpleDateFormat("yyyyMMddHH");
    public static final DateFormat bf = new SimpleDateFormat("yyyyMMdd");


    @SneakyThrows
    public static Instant truncateMinutes(Instant from) {
        String formattedString = df.format(Date.from(from));
        return df.parse(formattedString).toInstant();
    }

    @SneakyThrows
    public static String dateFormatForBilling(Instant from) {
        return bf.format(Date.from(from));
    }

    /** Code from
     * https://mkyong.com/java/java-convert-ip-address-to-decimal-number/
     * * @param ipAddress
     * @return numeric interpretation of the IP address
     */
    public static long ipToLong(String ipAddress) {

        String[] ipAddressInArray = ipAddress.split("\\.");

        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {

            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);

        }

        return result;
    }

    public static String ipFromLong(long ip) {
        StringBuilder result = new StringBuilder(15);

        for (int i = 0; i < 4; i++) {

            result.insert(0, ip & 0xff);

            if (i < 3) {
                result.insert(0,'.');
            }

            ip = ip >> 8;
        }
        return result.toString();
    }

    @SneakyThrows
    public static Instant truncateHours(Instant date) {
        String formattedString = bf.format(Date.from(date));
        return bf.parse(formattedString).toInstant();
    }

    public static ObjectMapper buildObjectMapper() {
        return new ObjectMapper().findAndRegisterModules()
                .registerModule(new JavaTimeModule())
                .configure(
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true
                )
                .configure(
                        SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false
                );
    }
}
