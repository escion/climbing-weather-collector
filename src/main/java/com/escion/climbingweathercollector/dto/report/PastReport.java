package com.escion.climbingweathercollector.dto.report;

import lombok.Data;

import java.util.Map;

@Data
public class PastReport extends WeatherReport{
    Map<Integer, Weather> pastHourly;
}
