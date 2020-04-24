package com.escion.climbingweathercollector.service;

import com.escion.climbingweathercollector.model.cache.WeatherConditionCacheable;

import java.util.Optional;

public interface CacheService {
    void put(WeatherConditionCacheable data);
    void putIfAbsent(WeatherConditionCacheable data);
    Optional<WeatherConditionCacheable> getById(String id);
    void deleteById(String id);
}
