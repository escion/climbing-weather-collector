
package com.escion.climbingweathercollector.dto.openweatherapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dt",
    "sunrise",
    "sunset",
    "temp",
    "feels_like",
    "pressure",
    "humidity",
    "dew_point",
    "uvi",
    "clouds",
    "visibility",
    "wind_speed",
    "wind_deg",
    "weather"
})
public class Current extends CommonWeather{

    @JsonProperty("sunrise")
    public Integer sunrise;
    @JsonProperty("sunset")
    public Integer sunset;
    @JsonProperty("temp")
    public Double temp;
    @JsonProperty("feels_like")
    public Double feelsLike;
    @JsonProperty("uvi")
    public Double uvi;
    @JsonProperty("visibility")
    public Integer visibility;
    @JsonProperty("weather")
    public List<WeatherDescription> weather = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
