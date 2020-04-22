package com.escion.climbingweathercollector;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.WeatherReport;
import com.escion.climbingweathercollector.model.WeatherCondition;
import com.escion.climbingweathercollector.service.UnavailableServiceException;
import com.escion.climbingweathercollector.service.impl.OpenWeatherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import java.util.Optional;

@Slf4j
@SpringBootTest
class ClimbingWeatherCollectorApplicationTests {

	@Autowired
	private OpenWeatherServiceImpl weatherService;

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	void past(){
		Double lat = 45.63;
		Double lon = 9.15;
		Position position = new Position(lat,lon);
		Optional<WeatherReport> condition = weatherService.getPastConditions(position, "1587427200");
		Assertions.assertNotNull(condition);
		Assertions.assertTrue(condition.isPresent());
		Optional<WeatherReport> conditionErr = weatherService.getPastConditions(position, null);
		Assertions.assertNotNull(conditionErr);
		Assertions.assertFalse(conditionErr.isPresent());
	}
}
