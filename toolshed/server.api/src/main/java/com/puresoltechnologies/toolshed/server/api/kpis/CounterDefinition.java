package com.puresoltechnologies.toolshed.server.api.kpis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;

public class CounterDefinition extends KPIDefinition {

    @JsonCreator
    public CounterDefinition( //
	    @JsonProperty("name") String name, //
	    @JsonProperty("description") String description //
    ) {
	super(name, "", description, LevelOfMeasurement.RATIO);
    }

}
