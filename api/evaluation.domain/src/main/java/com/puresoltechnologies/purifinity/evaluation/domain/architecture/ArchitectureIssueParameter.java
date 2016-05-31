package com.puresoltechnologies.purifinity.evaluation.domain.architecture;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;

/**
 * This is a special parameter for architecture issues. The generic for
 * {@link ParameterWithArbitraryUnit} is {@link Integer} to have a better way to
 * calculate issues. The default value should be '1' to reflect a single issue,
 * but it may be meaningful to have a higher value to have double issues or
 * another weighting for a single issue.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ArchitectureIssueParameter extends ParameterWithArbitraryUnit<Integer> {

    public static ArchitectureIssueParameter create(String parameterName, String parameterUnit,
	    String parameterDescription) {
	return new ArchitectureIssueParameter(parameterName, parameterUnit, parameterDescription);
    }

    private static final long serialVersionUID = -2759994739756661433L;

    @JsonCreator
    public ArchitectureIssueParameter(@JsonProperty("name") String name, @JsonProperty("unit") String unit,
	    @JsonProperty("description") String description) {
	super(name, unit, LevelOfMeasurement.NOMINAL, description, Integer.class);
    }

}
