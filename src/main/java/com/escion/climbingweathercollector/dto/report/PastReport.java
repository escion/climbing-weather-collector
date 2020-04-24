package com.escion.climbingweathercollector.dto.report;

import lombok.Data;

import java.util.Map;

@Data
public class PastReport extends WeatherReport{
    private Map<Integer, Weather> pastHourly;
}
