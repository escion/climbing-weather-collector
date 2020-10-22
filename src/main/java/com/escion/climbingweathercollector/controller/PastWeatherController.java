package com.escion.climbingweathercollector.controller;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.PastReport;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.model.cache.WeatherConditionCacheable;
import com.escion.climbingweathercollector.service.CacheService;
import com.escion.climbingweathercollector.service.CragService;
import com.escion.climbingweathercollector.service.WeatherDataService;
import com.escion.climbingweathercollector.utils.CommonsUtils;
import com.escion.climbingweathercollector.utils.transformer.common.WeatherConditionCacheableMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private CragService cragService;

    public List<WeatherConditionCacheable> aggregatePastWeather(Position position, String timestamp){
        Optional<PastReport> report = (Optional<PastReport>) weatherDataService.getPastConditions(position, timestamp);
        if(isCacheable(report)){
            PastReport pastReport = report.get();
            Map<String, List<Weather>> aggregatedByPeriod = pastReport.getPastHourly().values().stream().collect(Collectors.groupingBy(Weather::getPeriod));
            List<Optional<Weather>> averagedByPeriod = aggregatedByPeriod.values().stream().map(list -> list.stream().reduce((weather, weather2) -> average(weather, weather2))).collect(Collectors.toList());
            return averagedByPeriod.stream().map(w -> WeatherConditionCacheableMapper.toCacheable(w.get(), position)).collect(Collectors.toList());
        }
        else{
            log.warn("Past weather data not available.");
            return Collections.emptyList();
        }
    }

    public void saveCacheableData(WeatherConditionCacheable data){
        cacheService.putIfAbsent(data);
    }

    private static Weather average(Weather weather1, Weather weather2){
        weather1.setTemperature(CommonsUtils.average(weather1.getTemperature(), weather2.getTemperature()));
        weather1.setFeelsLikeTemperature(CommonsUtils.average(weather1.getFeelsLikeTemperature(), weather2.getFeelsLikeTemperature()));
        weather1.setClouds(CommonsUtils.average(weather1.getClouds(), weather2.getClouds()));
        weather1.setHumidity(CommonsUtils.average(weather1.getHumidity(), weather2.getHumidity()));
        weather1.setWindSpeed(CommonsUtils.average(weather1.getWindSpeed(), weather2.getWindSpeed()));
        weather1.setPressure(CommonsUtils.average(weather1.getPressure(), weather2.getPressure()));
        weather1.setWindDirection(CommonsUtils.average(weather1.getWindDirection(), weather2.getWindDirection()));
        weather1.setRainVolume(CommonsUtils.average(weather1.getRainVolume(), weather2.getRainVolume()));
        weather1.setSnowVolume(CommonsUtils.average(weather1.getSnowVolume(), weather2.getSnowVolume()));
        return weather1;
    }

    private static boolean isCacheable(Optional<PastReport> report){
        return report.isPresent() && MapUtils.isNotEmpty(report.get().getPastHourly());
    }
}