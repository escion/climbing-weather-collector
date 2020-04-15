package com.escion.climbingweathercollector.service;

import com.escion.climbingweathercollector.dto.Position;
import com.escion.climbingweathercollector.dto.Weather;

import java.util.Collection;

public interface WeatherService {
    Collection<Weather> getForecast(Position position) throws UnavailableServiceException;
    Collection<Weather> getPastConditions(Position position, String timestamp) throws UnavailableServiceException;
}
