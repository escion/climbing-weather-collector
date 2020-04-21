package com.escion.climbingweathercollector.dto.report;

import com.escion.climbingweathercollector.dto.common.Position;
import lombok.Data;

@Data
public abstract class WeatherReport {
    Position position;
    String timezone;
}
