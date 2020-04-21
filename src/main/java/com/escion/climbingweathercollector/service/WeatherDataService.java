package com.escion.climbingweathercollector.service;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.dto.report.WeatherReport;

import java.util.Collection;

public interface WeatherDataService {
    WeatherReport getForecast(Position position) throws UnavailableServiceException;
    WeatherReport getPastConditions(Position position, String timestamp) throws UnavailableServiceException;
}
