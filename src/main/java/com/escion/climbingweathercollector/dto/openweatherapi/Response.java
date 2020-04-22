
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
    "lat",
    "lon",
    "timezone",
    "current",
    "hourly",
    "daily"
})
public class Response {

    @JsonProperty("lat")
    public Double lat;
    @JsonProperty("lon")
    public Double lon;
    @JsonProperty("timezone")
    public String timezone;
    @JsonProperty("current")
    public Current current;
    @JsonProperty("hourly")
    public List<Hourly> hourly = null;
    @JsonProperty("daily")
    public List<Daily> daily = null;
}
