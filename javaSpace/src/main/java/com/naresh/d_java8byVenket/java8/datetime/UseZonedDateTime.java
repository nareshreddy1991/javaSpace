package com.naresh.d_java8byVenket.java8.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

class UseZonedDateTime {

    ZonedDateTime getZonedDateTime(LocalDateTime localDateTime, ZoneId zoneId) {
        return ZonedDateTime.of(localDateTime, zoneId);
    }
}
