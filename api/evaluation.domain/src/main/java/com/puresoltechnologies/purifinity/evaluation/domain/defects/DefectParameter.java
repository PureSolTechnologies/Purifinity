package com.puresoltechnologies.purifinity.evaluation.domain.defects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;

/**
 * This is a special parameter for defects. The generic for
 * {@link ParameterWithArbitraryUnit} is {@link Integer} to have a better way to
 * calculate issues. The default value should be '1' to reflect a single issue,
 * but it may be meaningful to have a higher value to have double issues or
 * another weighting for a single issue.
 * 
 * @author Rick-Rainer Ludwig
 */
public class DefectParameter extends ParameterWithArbitraryUnit<Integer> {

    public static DefectParameter create(String parameterName, String parameterUnit, String parameterDescription) {
	return new DefectParameter(parameterName, parameterUnit, parameterDescription);
    }

    private static final long serialVersionUID = -2759994739756661433L;

    @JsonCreator
    public DefectParameter(@JsonProperty("name") String name, @JsonProperty("unit") String unit,
	    @JsonProperty("description") String description) {
	super(name, unit, LevelOfMeasurement.NOMINAL, description, Integer.class);
    }

}
