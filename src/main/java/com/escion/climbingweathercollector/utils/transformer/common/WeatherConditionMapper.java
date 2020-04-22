package com.escion.climbingweathercollector.utils.transformer.common;

import com.escion.climbingweathercollector.dto.common.Position;
import com.escion.climbingweathercollector.dto.report.Weather;
import com.escion.climbingweathercollector.model.cache.WeatherCondition;
import org.apache.commons.lang3.StringUtils;

public class WeatherConditionMapper {

    public static WeatherCondition fromWeatherAndPosition(Weather weather, Position position){
        WeatherCondition condition = new WeatherCondition();
        condition.setKey(StringUtils.joinWith("_",position.getLat(),position.getLon(),weather.getTimestamp()));
        return condition;
    }
}
