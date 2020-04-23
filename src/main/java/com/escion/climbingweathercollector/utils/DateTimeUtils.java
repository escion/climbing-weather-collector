package com.escion.climbingweathercollector.utils;

import com.escion.climbingweathercollector.dto.common.Period;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class DateTimeUtils {

    public static String getPeriodOfDay(int timestamp, String timezone){
        int hour = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(timestamp)), TimeZone.getTimeZone(timezone).toZoneId()).getHour();
        if(hour >= 0 && hour <= 6)
            return Period.NIGHT.name();
        if(hour > 6 && hour <= 12)
            return Period.MORNING.name();
        if(hour > 12 && hour <= 18)
            return Period.AFTERNOON.name();
        return Period.EVENING.name();
    }
}
