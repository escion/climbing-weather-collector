package com.escion.climbingweathercollector.dto.report;

import lombok.Data;

@Data
public class Weather {
    Integer timestamp;
    String period;
    Double temperature;
    Double feelsLikeTemperature;
    Double pressure;
    Double humidity;
    Double clouds;
    Double windSpeed;
    Double windDirection;
    Integer rainVolume;
    Integer snowVolume;
    String description;
}
