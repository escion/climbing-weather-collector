package com.escion.climbingweathercollector.model.cache;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("WeatherCondition")
public class WeatherCondition implements Serializable {
    @Id
    String key;
}
