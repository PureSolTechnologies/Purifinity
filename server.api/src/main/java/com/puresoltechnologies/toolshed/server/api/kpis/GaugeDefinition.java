package com.puresoltechnologies.toolshed.server.api.kpis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;

public class GaugeDefinition extends KPIDefinition {

    @JsonCreator
    public GaugeDefinition( //
	    @JsonProperty("name") String name, //
	    @JsonProperty("unit") String unit, //
	    @JsonProperty("description") String description, //
	    @JsonProperty("levelOfMeasurement") LevelOfMeasurement levelOfMeasurement //
    ) {
	super(name, unit, description, levelOfMeasurement);
    }

}
