package com.escion.climbingweathercollector.utils.transformer.openweatherapi;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.openweatherapi.Hourly;
import com.escion.climbingweathercollector.dto.openweatherapi.Response;
import com.escion.climbingweathercollector.dto.report.PastReport;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.dto.report.WeatherReport;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeatherMapper{

    public static WeatherReport mapPast(Response response){
        if(response == null)
            return null;
        PastReport report = new PastReport();
        report.setPosition(new Position(response.lat, response.lon));
        report.setTimezone(response.timezone);
        //report.setPastHourly(mapHourly(response.hourly));
        return report;
    }

    private static Map<Integer, Weather> mapHourly(List<Hourly> hourly){
        return hourly.stream().map(h -> createWeatherCondition(h)).collect(Collectors.toMap(Weather::getTimestamp, weatherCondition -> weatherCondition));
    }

    private static Weather createWeatherCondition(Hourly hourly){
        Weather condition = new Weather();
        //condition.setTimestamp(hourly.dt);
        condition.setTemperature(hourly.temp);
        condition.setFeelsLikeTemperature(hourly.feelsLike);
        condition.setPressure(hourly.pressure);
        condition.setHumidity(hourly.humidity);
        condition.setClouds(hourly.clouds);
        condition.setWindSpeed(hourly.windSpeed);
        condition.setWindDirection(hourly.windDeg);
        condition.setRainVolume(hourly.rain);
        condition.setSnowVolume(hourly.snow);
        condition.setDescription(hourly.weather.get(0).description);
        return condition;
    }
}
