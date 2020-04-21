package com.escion.climbingweathercollector.service.impl;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.dto.openweatherapi.Response;
import com.escion.climbingweathercollector.dto.report.WeatherReport;
import com.escion.climbingweathercollector.service.UnavailableServiceException;
import com.escion.climbingweathercollector.service.WeatherDataService;
import com.escion.climbingweathercollector.utils.transformer.openweatherapi.WeatherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collection;

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

    public void getWeather(Integer id) throws UnavailableServiceException {
        //Assert.notNull(position, "Lat and lon must not be blank");
        ResponseEntity<Response> response = openWeatherTemplate.getForEntity(currentUrl, Response.class, String.valueOf(id));
        log.info("Response code: {}", response.getStatusCode());
        if(response.getStatusCode().is2xxSuccessful()){
            //Mapping e restituzione
        }
        else if(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            throw new UnavailableServiceException("API key expired.");
        }
        else if(response.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS)){
            throw new UnavailableServiceException("More than 60 requests in one minute.");
        }
        else
            throw new UnavailableServiceException("Error in calling openWeather service");
    }

    @Override
    public WeatherReport getForecast(Position position) throws UnavailableServiceException {
        Assert.notNull(position, "Lat and lon must not be blank");
        ResponseEntity<Response> response = openWeatherTemplate.getForEntity(forecastUrl, Response.class, position.getLat(), position.getLon());
        log.info("Response code: {}", response.getStatusCode());
        if(response.getStatusCode().is2xxSuccessful()){
            //Mapping e restituzione
            return null;
            //return WeatherMapper.map(response.getBody());
        }
        else if(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            throw new UnavailableServiceException("API key expired.");
        }
        else if(response.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS)){
            throw new UnavailableServiceException("More than 60 requests in one minute.");
        }
        else
            throw new UnavailableServiceException("Error in calling openWeather service");
    }

    @Override
    public WeatherReport getPastConditions(Position position, String timestamp) throws UnavailableServiceException {
        Assert.notNull(position, "Lat and lon must not be blank");
        Assert.notNull(timestamp, "Timestamp must not be null");
        ResponseEntity<Response> response = openWeatherTemplate.getForEntity(pastUrl, Response.class, position.getLat(), position.getLon(), timestamp);
        log.info("Response code: {}", response.getStatusCode());
        if(response.getStatusCode().is2xxSuccessful()){
            //Mapping e restituzione
            return WeatherMapper.mapPast(response.getBody());
        }
        else if(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            throw new UnavailableServiceException("API key expired.");
        }
        else if(response.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS)){
            throw new UnavailableServiceException("More than 60 requests in one minute.");
        }
        else
            throw new UnavailableServiceException("Error in calling openWeather service");
    }
}