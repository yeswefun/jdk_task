package com.z.j8.j812;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;

public class LocalDateTest {

    @Test
    public void testLocalDate() {
        //This class is immutable and thread-safe.
        LocalDate localDate = LocalDate.of(2016, 11, 13);
        System.out.println(localDate.getYear());

        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());

        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getDayOfMonth());
        System.out.println(localDate.getDayOfWeek());
    }

    @Test
    public void testLocalTime() {
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour());
        System.out.println(time.getMinute());
        System.out.println(time.getSecond());
    }

    @Test
    public void testX() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getHour());
        System.out.println(localDateTime);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
    }

    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
    }

    @Test
    public void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.getSeconds());
        System.out.println(duration.toMillis());
    }

    @Test
    public void testDuration() {
        LocalTime start = LocalTime.now();
        LocalTime beforeTime = start.minusHours(1);
        Duration duration = Duration.between(start, beforeTime);
        System.out.println(duration.toMillis());
        System.out.println(duration.getSeconds());
    }

    @Test
    public void testPeriod() {
        LocalDate start = LocalDate.of(2015, 6, 1);
        LocalDate end = LocalDate.of(2023, 10, 1);
        Period period = Period.between(start, end);
        System.out.println(period.getYears());
        System.out.println(period.toTotalMonths());
        System.out.println(period.getDays());   // error
        System.out.println(period.getMonths()); // error
    }

    /*
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date())
     */
    @Test
    public void testFormat() {
        LocalDate localDate = LocalDate.now();
        String r = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(r);

        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
//        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(localDate.format(dtf));
    }

    @Test
    public void testDateParse() {
        String date1 = "20231130";
        LocalDate localDate = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(date1, dtf);
        System.out.println(localDate1.getYear());
    }
}
