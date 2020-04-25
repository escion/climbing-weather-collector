package com.escion.climbingweathercollector.utils.transformer.openweatherapi;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.openweatherapi.Hourly;
import com.escion.climbingweathercollector.dto.openweatherapi.Response;
import com.escion.climbingweathercollector.dto.openweatherapi.Volume;
import com.escion.climbingweathercollector.dto.report.PastReport;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.dto.report.WeatherReport;
import com.escion.climbingweathercollector.utils.CommonsUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OpenWeatherApiMapper {

    public static WeatherReport mapPast(Response response) {
        if(response == null)
            return null;
        PastReport report = new PastReport();
        report.setPosition(new Position(response.lat, response.lon));
        report.setTimezone(response.timezone);
        report.setPastHourly(mapHourly(response.hourly, response.timezone));
        return report;
    }

    private static Map<Integer, Weather> mapHourly(List<Hourly> hourly, String timezone){
        return hourly.stream().map(h -> createWeather(h, timezone)).collect(Collectors.toMap(Weather::getTimestamp, weather -> weather));
    }

    private static Weather createWeather(Hourly hourly, String timezone){
        Weather condition = new Weather();
        condition.setTimestamp(hourly.dt);
        condition.setPeriod(CommonsUtils.getPeriodOfDay(hourly.dt, timezone));
        condition.setTemperature(hourly.temp);
        condition.setFeelsLikeTemperature(hourly.feelsLike);
        condition.setPressure(hourly.pressure);
        condition.setHumidity(hourly.humidity);
        condition.setClouds(hourly.clouds);
        condition.setWindSpeed(hourly.windSpeed);
        condition.setWindDirection(hourly.windDeg);
        condition.setRainVolume(getVolume(hourly.rain));
        condition.setSnowVolume(getVolume(hourly.snow));
        condition.setDescription(hourly.weather.get(0).description);
        return condition;
    }

    private static Double getVolume(Volume value){
        if(value == null)
            return 0.0;
        if(value.lastHour != null)
            return value.lastHour;
        return value.lastThreeHours;
    }
}
