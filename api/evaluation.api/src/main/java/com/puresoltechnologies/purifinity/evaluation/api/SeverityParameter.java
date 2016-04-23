package com.puresoltechnologies.purifinity.evaluation.api;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;

public class SeverityParameter extends ParameterWithArbitraryUnit<Severity> {

    private static final long serialVersionUID = -931199455057090001L;

    public static final String NAME = "source code quality";

    private static final SeverityParameter INSTANCE = new SeverityParameter();

    public static SeverityParameter getInstance() {
	return INSTANCE;
    }

    private SeverityParameter() {
	super(NAME, "", LevelOfMeasurement.ORDINAL, "Severity measure of related source code range.", Severity.class);
    }

}
