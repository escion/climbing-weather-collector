package com.escion.climbingweathercollector.dto.openweatherapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CommonWeather {
    @JsonProperty("dt")
    public Integer dt;
    @JsonProperty("pressure")
    public Double pressure;
    @JsonProperty("humidity")
    public Double humidity;
    @JsonProperty("dew_point")
    public Double dewPoint;
    @JsonProperty("clouds")
    public Double clouds;
    @JsonProperty("wind_speed")
    public Double windSpeed;
    @JsonProperty("wind_deg")
    public Double windDeg;
    @JsonProperty("rain")
    @JsonIgnore
    public Volume rain;
    @JsonProperty("snow")
    @JsonIgnore
    public Volume snow;
}
