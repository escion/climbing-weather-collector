package com.escion.climbingweathercollector.controller;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.PastReport;
import com.escion.climbingweathercollector.dto.report.WeatherReport;
import com.escion.climbingweathercollector.service.CacheService;
import com.escion.climbingweathercollector.service.UnavailableServiceException;
import com.escion.climbingweathercollector.service.WeatherDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class PastWeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private CacheService cacheService;

    public void getAndStorePastWeather(Position position, Integer timestamp){
        try{
            Optional<PastReport> report = (Optional<PastReport>) weatherDataService.getPastConditions(position, String.valueOf(timestamp));

        }
        catch(UnavailableServiceException e){
            log.error("Weather data cannot be retrieved: ", e);
        }
    }
}
