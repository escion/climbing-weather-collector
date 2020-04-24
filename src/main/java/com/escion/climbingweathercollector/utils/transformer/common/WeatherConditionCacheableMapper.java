
package com.escion.climbingweathercollector.utils.transformer.common;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.model.cache.WeatherConditionCacheable;
import org.apache.commons.lang3.StringUtils;

public class WeatherConditionCacheableMapper{

    public static WeatherConditionCacheable toCacheable(Weather weather, Position position){
        WeatherConditionCacheable condition = new WeatherConditionCacheable();
        condition.setKey(StringUtils.joinWith("_", position.getLatitude(), position.getLongitude(), weather.getTimestamp()));
        condition.setPeriod(weather.getPeriod());
        condition.setTemperature(weather.getTemperature());
        condition.setFeelsLikeTemperature(weather.getFeelsLikeTemperature());
        condition.setHumidity(weather.getHumidity());
        condition.setRainVolume(weather.getRainVolume());
        condition.setSnowVolume(weather.getSnowVolume());
        return condition;
    }
}
