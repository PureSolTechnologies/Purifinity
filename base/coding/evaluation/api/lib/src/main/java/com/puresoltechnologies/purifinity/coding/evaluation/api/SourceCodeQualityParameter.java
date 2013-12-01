package com.puresoltechnologies.purifinity.coding.evaluation.api;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;

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
