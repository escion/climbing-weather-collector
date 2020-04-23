package com.escion.climbingweathercollector.controller;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.PastReport;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.service.CacheService;
import com.escion.climbingweathercollector.service.WeatherDataService;
import com.escion.climbingweathercollector.utils.transformer.common.WeatherConditionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PastWeatherController {

    @Autowired
    @Qualifier("openWeatherService")
    private WeatherDataService weatherDataService;

    @Autowired
    @Qualifier("redisService")
    private CacheService cacheService;

    public void getAndStorePastWeather(Position position, String timestamp){
        Optional<PastReport> report = (Optional<PastReport>) weatherDataService.getPastConditions(position, timestamp);
        if(isCacheable(report)){
            PastReport pastReport = report.get();
            Collection<List<Weather>> aggregated = pastReport.getPastHourly().values().stream().collect(Collectors.groupingBy(Weather::getPeriod)).values();
        }
    }

    private static boolean isCacheable(Optional<PastReport> report){
        return report.isPresent() && MapUtils.isNotEmpty(report.get().getPastHourly());
    }
}
