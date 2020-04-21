package com.escion.climbingweathercollector;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.service.UnavailableServiceException;
import com.escion.climbingweathercollector.service.impl.OpenWeatherServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

@Slf4j
@SpringBootTest
class ClimbingWeatherCollectorApplicationTests {

	@Autowired
	private OpenWeatherServiceImpl weatherService;

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	void forecast() {
		Double lat = 45.63;
		Double lon = 9.15;
		for(int i=0;i<100;i++){
			Position position = new Position(lat,lon);
			try{
				log.info("i: {} lat {} lon {}",i,lat,lon);
				weatherService.getForecast(position);
				lat = lat + 0.1;
				lon = lon + 0.1;
			}
			catch(UnavailableServiceException e){
				log.error("Errore {}",e);
			}
		}
	}

	@Test
	void past(){
		Double lat = 45.63;
		Double lon = 9.15;
		for(int i=0;i<100;i++){
			Position position = new Position(lat,lon);
			try{
				log.info("i: {} lat {} lon {}",i,lat,lon);
				weatherService.getPastConditions(position, "1586563200");
				lat = lat + 0.1;
				lon = lon + 0.1;
			}
			catch(UnavailableServiceException e){
				log.error("Errore {}",e);
			}
		}
	}

	@Test
	void current(){
		Double lat = 45.63;
		Double lon = 9.15;
		Integer id = 62854;
		for(int i=0;i<300;i++){
			Position position = new Position(lat,lon);
			try{
				//log.info("i: {} lat {} lon {}",i,lat,lon);
				weatherService.getWeather(id);
				//id+=1;
				lat = lat + 0.1;
				lon = lon + 0.1;
			}
			catch(UnavailableServiceException e){
				log.error("Errore {}",e);
			}
		}
	}
}
