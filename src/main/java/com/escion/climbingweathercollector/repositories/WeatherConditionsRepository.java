package com.escion.climbingweathercollector.repositories;

import com.escion.climbingweathercollector.model.cache.WeatherConditionCacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherConditionsRepository extends CrudRepository<WeatherConditionCacheable, String> {
}
