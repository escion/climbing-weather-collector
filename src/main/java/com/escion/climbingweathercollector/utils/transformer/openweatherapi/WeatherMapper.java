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
        PastReport report = new PastReport();
        report.setPosition(new Position(response.lat, response.lon));
        report.setTimezone(response.timezone);
        report.setPastHourly(mapHourly(response.hourly));
        return report;
    }

    private static Map<Integer, Weather> mapHourly(List<Hourly> hourly){
        return hourly.stream().map(h -> createWeatherCondition(h)).collect(Collectors.toMap(Weather::getTimestamp, weatherCondition -> weatherCondition));
    }

    private static Weather createWeatherCondition(Hourly hourly){
        Weather condition = new Weather();
        condition.setTimestamp(hourly.dt);
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

    /**
    public static WeatherReport mapForecast(Response response){
        WeatherReport weather = new WeatherReport();
        Position position = new Position(response.lat, response.lon);
        weather.setPosition(position);
        weather.setTimezone(response.timezone);
        if(response.current != null){
            WeatherCondition currentWeather = new WeatherCondition();
            Current current = response.current;
            weather.setTimestamp(current.dt);
            currentWeather.setTemperature(current.temp);
            currentWeather.setFeelsLikeTemperature(current.feelsLike);
            currentWeather.setHumidity(current.humidity);
            currentWeather.setPressure(current.pressure);
            currentWeather.setWindSpeed(current.windSpeed);
            currentWeather.setWindDirection(current.windDeg);
            currentWeather.setRainVolume(current.rain);
            currentWeather.setSnowVolume(current.snow);
            weather.setCurrent(currentWeather);
        }
        if(response.daily != null && response.daily.size() > 0){
            Map<Integer, WeatherCondition> dailyMap = new HashMap<>(response.daily.size());
            for(Daily daily : response.daily){
                WeatherCondition dailyCondition = mapDaily(daily);
                dailyMap.put(dailyCondition.getTimestamp(), dailyCondition);
            }
            weather.setDailyForecast(dailyMap);
        }
        if(response.hourly != null && response.hourly.size() > 0){
            Map<Integer, WeatherCondition> hourlyMap = new HashMap<>(response.hourly.size());
            for(Hourly hourly : response.hourly){
                WeatherCondition hourlyCondition = mapHourly(hourly);
                hourlyMap.put(hourlyCondition.getTimestamp(), hourlyCondition);
            }
            weather.setHourlyForecast(hourlyMap);
        }
        return weather;
    }

    private static WeatherCondition mapDaily(Daily daily){
        WeatherCondition weatherCondition = new WeatherCondition();
        weatherCondition.setTimestamp(daily.dt);
        weatherCondition.setTemperature(daily.temp.day);
        return weatherCondition;
    }

    private static WeatherCondition mapHourly(Hourly hourly){
        WeatherCondition weatherCondition = new WeatherCondition();
        return weatherCondition;
    }
     */
}
