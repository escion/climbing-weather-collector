package com.escion.climbingweathercollector.service.impl;

import com.escion.climbingweathercollector.model.WeatherCondition;
import com.escion.climbingweathercollector.repositories.WeatherConditionsRepository;
import com.escion.climbingweathercollector.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Qualifier("redisService")
@Service
public class RedisServiceImpl implements CacheService {

    @Autowired
    private WeatherConditionsRepository repository;

    @Override
    public void put(WeatherCondition data) {
        repository.save(data);
    }

    @Override
    public void putIfAbsent(WeatherCondition data) {
        if(!repository.existsById(data.getId()))
            repository.save(data);
    }

    @Override
    public Optional<WeatherCondition> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
