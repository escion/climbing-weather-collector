package com.escion.climbingweathercollector.service.impl;

import com.escion.climbingweathercollector.model.cache.WeatherConditionCacheable;
import com.escion.climbingweathercollector.repositories.WeatherConditionsRepository;
import com.escion.climbingweathercollector.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Qualifier("redisService")
@Service
public class RedisServiceImpl implements CacheService {

    @Autowired
    private WeatherConditionsRepository repository;

    @Override
    public void put(WeatherConditionCacheable data) {
        repository.save(data);
    }

    @Override
    public void putIfAbsent(WeatherConditionCacheable data) {
        if(!repository.existsById(data.getKey())){
            repository.save(data);
            log.info("Put data with key: {}", data.getKey());
        }
        else{
            log.info("Data with key: {} already exists", data.getKey());
        }
    }

    @Override
    public Optional<WeatherConditionCacheable> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
