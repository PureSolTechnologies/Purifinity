package com.puresoltechnologies.purifinity.evaluation.api;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class SourceCodeQualityParameter extends
		ParameterWithArbitraryUnit<SourceCodeQuality> {

	private static final long serialVersionUID = -931199455057090001L;

	public static final String NAME = "source code quality";

	private static final SourceCodeQualityParameter INSTANCE = new SourceCodeQualityParameter();

	public static SourceCodeQualityParameter getInstance() {
		return INSTANCE;
	}

	private SourceCodeQualityParameter() {
		super(NAME, "", LevelOfMeasurement.ORDINAL,
				"Quality measure of related source code range.",
				SourceCodeQuality.class);
	}

}
