package com.escion.climbingweathercollector.controller;

import com.escion.climbingweathercollector.dto.common.Period;
import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.openweatherapi.Response;
import com.escion.climbingweathercollector.dto.report.PastReport;
import com.escion.climbingweathercollector.dto.report.WeatherReport;
import com.escion.climbingweathercollector.model.cache.WeatherConditionCacheable;
import com.escion.climbingweathercollector.service.impl.OpenWeatherServiceImpl;
import com.escion.climbingweathercollector.service.impl.RedisServiceImpl;
import com.escion.climbingweathercollector.utils.transformer.openweatherapi.OpenWeatherApiMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PastWeatherControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private RedisServiceImpl redisService;

    @Mock
    private OpenWeatherServiceImpl service;

    @InjectMocks
    private PastWeatherController controller;

    @Test
    public void testAggregationOK() throws IOException {
        Position position = new Position(44.35, 9.15);
        String timestamp = "1587427200";
        Optional<WeatherReport> report = readMockData("past_weather.json");
        Mockito.when(service.getPastConditions(Mockito.any(Position.class), Mockito.anyString())).thenReturn(report);
        List<WeatherConditionCacheable> data = controller.aggregatePastWeather(position, timestamp);
        assertTrue(CollectionUtils.size(data) == 4);
        assertTrue(data.stream().filter(w -> w.getPeriod().equals(Period.NIGHT.name())).collect(Collectors.toList()).size() == 1);
        assertTrue(data.stream().filter(w -> w.getPeriod().equals(Period.MORNING.name())).collect(Collectors.toList()).size() == 1);
        assertTrue(data.stream().filter(w -> w.getPeriod().equals(Period.AFTERNOON.name())).collect(Collectors.toList()).size() == 1);
        assertTrue(data.stream().filter(w -> w.getPeriod().equals(Period.EVENING.name())).collect(Collectors.toList()).size() == 1);
    }

    @Test
    public void testAverageValueOK() throws IOException {
        Position position = new Position(44.35, 9.15);
        String timestamp = "1587427200";
        Optional<WeatherReport> report = readMockData("past_weather.json");
        Mockito.when(service.getPastConditions(Mockito.any(Position.class), Mockito.anyString())).thenReturn(report);
        List<WeatherConditionCacheable> data = controller.aggregatePastWeather(position, timestamp);
        List<WeatherConditionCacheable> cache = data.stream().filter(w -> w.getPeriod().equals(Period.NIGHT.name())).collect(Collectors.toList());
        Assertions.assertEquals(10.0933333, cache.get(0).getTemperature());
    }

    @Test
    public void testAggregationKO() throws IOException {
        Position position = new Position(44.35, 9.15);
        String timestamp = "1587427200";
        Optional<WeatherReport> report = Optional.empty();
        Mockito.when(service.getPastConditions(Mockito.any(Position.class), Mockito.anyString())).thenReturn(report);
        List<WeatherConditionCacheable> data = controller.aggregatePastWeather(position, timestamp);
        assertTrue(CollectionUtils.isEmpty(data));
    }

    private Optional<WeatherReport> readMockData(String fileName) throws IOException {
        Optional<WeatherReport> opt = Optional.empty();
        Response response = mapper.readValue(new File("src/test/resources/mock/openweatherapi/" + fileName), Response.class);
        PastReport report = (PastReport) OpenWeatherApiMapper.mapPast(response);
        return opt.of(report);
    }
}
