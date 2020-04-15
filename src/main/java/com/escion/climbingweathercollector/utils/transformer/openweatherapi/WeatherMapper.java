package com.escion.climbingweathercollector.utils.transformer.openweatherapi;

import com.escion.climbingweathercollector.dto.Weather;
import com.escion.climbingweathercollector.dto.openweatherapi.Response;

import java.util.Arrays;
import java.util.Collection;

public class WeatherMapper{

    public static Collection<Weather> map(Response response){
        Weather weather = new Weather();
        weather.setId("test");
        weather.setName("test_name");
        return Arrays.asList(weather);
    }
}
