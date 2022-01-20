package com.venkat.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/*
Issues With the Existing Date/Time APIs
Thread safety – The Date and Calendar classes are not thread safe, leaving developers to deal with the headache of hard-to-debug concurrency issues and to write additional code to handle thread safety. On the contrary, the new Date and Time APIs introduced in Java 8 are immutable and thread safe, thus taking that concurrency headache away from developers.
API design and ease of understanding – The Date and Calendar APIs are poorly designed with inadequate methods to perform day-to-day operations. The new Date/Time API is ISO-centric and follows consistent domain models for date, time, duration and periods. There are a wide variety of utility methods that support the most common operations.
ZonedDate and Time – Developers had to write additional logic to handle time-zone logic with the old APIs, whereas with the new APIs, handling of time zone can be done with Local and ZonedDate/Time APIs.
 */
public class H_LocalDateTime {
    public static void main(String[] args) {
        //TODO LocalDate -- only date
        LocalDate localDate = LocalDate.now(); //yyyy-MM-dd default format
        System.out.println("localDate:" + localDate);
        LocalDate of = LocalDate.of(2015, 02, 20);
        LocalDate parse = LocalDate.parse("2015-02-20");
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();

        int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
        boolean leapYear = LocalDate.now().isLeapYear();
        boolean notBefore = LocalDate.parse("2016-06-12")
                .isBefore(LocalDate.parse("2016-06-11"));

        boolean isAfter = LocalDate.parse("2016-06-12")
                .isAfter(LocalDate.parse("2016-06-11"));

        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12")
                .with(TemporalAdjusters.firstDayOfMonth());

        //TODO LocalTime - time without a date
        LocalTime now = LocalTime.now(); //15:02:21.948
        System.out.println("time" + now);
        LocalTime sixThirty = LocalTime.parse("06:30");
        LocalTime sixThirty2 = LocalTime.of(6, 30);
        LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
        int six = LocalTime.parse("06:30").getHour();
        boolean isbefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
        LocalTime maxTime = LocalTime.MAX; //23:59:59.99:

        //TODO LocalDateTime - both date & time
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1); //2022-01-20T15:10:24.826
        LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        LocalDateTime.parse("2015-02-20T06:30:00");
        now1.plusDays(1);
        now1.minusHours(2);
        now1.getMonth();

        //TODO Zones
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(now1, zoneId);//converting LocalDateTime to ZonedDateTime
        ZonedDateTime parse1 = ZonedDateTime.parse("2015-05-03T10:15:30+01:00[Europe/Paris]");

        //TODO Period, Duration
        //Period - deals with >days, months, years
        LocalDate initialDate = LocalDate.parse("2007-05-10");
        LocalDate finalDate = initialDate.plus(Period.ofDays(5));
        int five = Period.between(initialDate, finalDate).getDays();
        long five1 = ChronoUnit.DAYS.between(initialDate, finalDate);
        //Duration -- deals with time
        LocalTime initialTime = LocalTime.of(6, 30, 0);
        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
        long thirty = Duration.between(initialTime, finalTime).getSeconds();
        long thirty2 = ChronoUnit.SECONDS.between(initialTime, finalTime);

        //TODO Date, Calendar to new DateTIme
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());

        //TODO Date time formatting
        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30);
        String localDateString = localDateTime.format(DateTimeFormatter.ISO_DATE); //2015-01-25:
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));


    }
}
