package com.escion.climbingweathercollector.service;

import com.escion.climbingweathercollector.model.cache.WeatherCondition;

import java.util.Optional;

public interface CacheService {
    void put(WeatherCondition data);
    void putIfAbsent(WeatherCondition data);
    Optional<WeatherCondition> getById(String id);
    void deleteById(String id);
}
