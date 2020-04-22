package com.escion.climbingweathercollector.service;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.dto.report.WeatherReport;

import java.util.Collection;
import java.util.Optional;

public interface WeatherDataService {
    Optional<? extends WeatherReport> getForecast(Position position);
    Optional<? extends WeatherReport> getPastConditions(Position position, String timestamp);
}
