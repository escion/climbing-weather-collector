
package com.escion.climbingweathercollector.utils.transformer.common;

import com.escion.climbingweathercollector.dto.common.Period;
import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.model.cache.WeatherCondition;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class WeatherConditionMapper {

    public static WeatherCondition createWeatherCondition(Weather weather, Position position, String timezone){
        WeatherCondition condition = new WeatherCondition();
        condition.setKey(StringUtils.joinWith("_", position.getLat(), position.getLon(), weather.getTimestamp()));
        condition.setPeriod(getPeriodOfDay(weather.getTimestamp(), timezone));
        condition.setTemperature(weather.getTemperature());
        condition.setFeelsLikeTemperature(weather.getFeelsLikeTemperature());
        condition.setHumidity(weather.getHumidity());
        condition.setRainVolume(weather.getRainVolume());
        condition.setSnowVolume(weather.getSnowVolume());
        return condition;
    }

    private static String getPeriodOfDay(int timestamp, String timezone){
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
