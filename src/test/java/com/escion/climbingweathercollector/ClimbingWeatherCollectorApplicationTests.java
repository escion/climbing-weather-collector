package com.escion.climbingweathercollector;

import com.escion.climbingweathercollector.controller.PastWeatherController;
import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.WeatherReport;
import com.escion.climbingweathercollector.service.CacheService;
import com.escion.climbingweathercollector.service.impl.OpenWeatherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.util.Optional;

@Slf4j
@SpringBootTest
class ClimbingWeatherCollectorApplicationTests {

	@Autowired
	private PastWeatherController controller;

	@Autowired
	private CacheService cacheService;

	@Autowired
	private OpenWeatherServiceImpl weatherService;

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	void testGetPastWeatherData(){
		Double lat = 45.63;
		Double lon = 9.15;
		Position position = new Position(lat,lon);
		Optional<WeatherReport> condition = weatherService.getPastConditions(position, "1587427200");
		Assertions.assertNotNull(condition);
		Assertions.assertTrue(condition.isPresent());
	}

	@Test
	public void testGetPastWeatherDataAndStoreInCache(){
		Position position = new Position(45.63, 9.15);
		controller.getAndStorePastWeather(position, "1587340800");
	}
}
