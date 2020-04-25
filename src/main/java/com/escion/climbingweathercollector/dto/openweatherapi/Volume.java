package com.escion.climbingweathercollector.dto.openweatherapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "1h",
        "3h"
})
public class Volume {
    @JsonProperty("1h")
    public Double lastHour;
    @JsonProperty("3h")
    public Double lastThreeHours;
}
