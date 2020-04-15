package com.escion.climbingweathercollector.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("WeatherCondition")
public class WeatherCondition implements Serializable {

    String id;
    String name;
}
