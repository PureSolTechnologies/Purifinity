package com.puresoltechnologies.toolshed.server.api.kpis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;

public class TimerDefinition extends KPIDefinition {

    @JsonCreator
    public TimerDefinition( //
	    @JsonProperty("name") String name, //
	    @JsonProperty("unit") String unit, //
	    @JsonProperty("description") String description //
    ) {
	super(name, unit, description, LevelOfMeasurement.RATIO);
    }

}
