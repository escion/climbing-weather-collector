package com.escion.climbingweathercollector.service.impl;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.openweatherapi.Response;
import com.escion.climbingweathercollector.dto.report.WeatherReport;
import com.escion.climbingweathercollector.service.WeatherDataService;
import com.escion.climbingweathercollector.utils.transformer.openweatherapi.OpenWeatherApiMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TimeZone;

@Slf4j
@Qualifier("openWeatherService")
@Service
public class OpenWeatherServiceImpl implements WeatherDataService {

    @Value("${openweather.forecast.url}")
    String forecastUrl;

    @Value("${openweather.past.url}")
    String pastUrl;

    @Value("${openweather.current.url}")
    String currentUrl;

    RestTemplate openWeatherTemplate;

    @PostConstruct
    private void init(){
        openWeatherTemplate = new RestTemplate();
    }

    /**
    public void getWeather(Integer id) throws UnavailableServiceException {
        //Assert.notNull(position, "Lat and lon must not be blank");
        ResponseEntity<Response> response = openWeatherTemplate.getForEntity(currentUrl, Response.class, String.valueOf(id));
        log.info("Response code: {}", response.getStatusCode());
        if(response.getStatusCode().is2xxSuccessful()){
            //Mapping e restituzione
        }
        manageException(response.getStatusCode());
    }
     */

    @Override
    public Optional<WeatherReport> getForecast(Position position){
        Assert.notNull(position, "Lat and lon must not be blank");
        ResponseEntity<Response> response = openWeatherTemplate.getForEntity(forecastUrl, Response.class, position.getLat(), position.getLon());
        log.info("Response code: {}", response.getStatusCode());
        if(response.getStatusCode().is2xxSuccessful()){
            //Mapping e restituzione
            return null;
            //return WeatherMapper.map(response.getBody());
        }
        return null;
        //manageException(response.getStatusCode());
    }

    @Override
    public Optional<WeatherReport> getPastConditions(Position position, String timestamp){
        Assert.notNull(position, "Lat and lon must not be blank");
        Assert.hasText(timestamp, "Timestamp must not be null");
        Response response = null;
        try{
            log.info("Retrieving past weather lat: {} lon: {} timestamp: {}", position.getLat(), position.getLon(), LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(timestamp)), TimeZone.getDefault().toZoneId()));
            response = openWeatherTemplate.getForObject(pastUrl, Response.class, position.getLat(), position.getLon(), timestamp);
        }
        catch(RestClientException e){
            log.error("Errore retrieving past weather: {}", e.getMessage());
        }
        return Optional.ofNullable(OpenWeatherApiMapper.mapPast(response));
    }
}