package com.escion.climbingweathercollector.repositories;

import com.escion.climbingweathercollector.model.WeatherCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherConditionsRepository extends CrudRepository<WeatherCondition, String> {
}
