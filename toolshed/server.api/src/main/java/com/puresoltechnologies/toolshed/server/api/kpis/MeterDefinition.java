package com.puresoltechnologies.toolshed.server.api.kpis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;

public class MeterDefinition extends KPIDefinition {

    @JsonCreator
    public MeterDefinition( //
	    @JsonProperty("name") String name, //
	    @JsonProperty("unit") String unit, //
	    @JsonProperty("description") String description, //
	    @JsonProperty("levelOfMeasurement") LevelOfMeasurement levelOfMeasurement //
    ) {
	super(name, unit, description, levelOfMeasurement);
    }

}
