package com.puresoltechnologies.purifinity.evaluation.domain.style;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;

public class StyleParameter extends ParameterWithArbitraryUnit<String> {

    private static final long serialVersionUID = -8769978674941438712L;

    @JsonCreator
    public StyleParameter(//
	    @JsonProperty("name") String name, //
	    @JsonProperty("description") String description) {
	super(name, "", LevelOfMeasurement.NOMINAL, description, String.class);
    }

}
