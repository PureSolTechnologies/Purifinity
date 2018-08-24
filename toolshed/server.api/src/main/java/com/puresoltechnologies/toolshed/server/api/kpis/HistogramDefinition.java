package com.puresoltechnologies.toolshed.server.api.kpis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;

public class HistogramDefinition extends KPIDefinition {

    @JsonCreator
    public HistogramDefinition( //
	    @JsonProperty("name") String name, //
	    @JsonProperty("unit") String unit, //
	    @JsonProperty("description") String description, //
	    @JsonProperty("levelOfMeasurement") LevelOfMeasurement levelOfMeasurement //
    ) {
	super(name, unit, description, levelOfMeasurement);
    }

}
