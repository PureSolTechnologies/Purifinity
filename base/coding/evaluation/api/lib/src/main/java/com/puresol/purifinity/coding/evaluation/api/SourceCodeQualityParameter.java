package com.puresol.purifinity.coding.evaluation.api;

import com.puresol.commons.utils.math.LevelOfMeasurement;
import com.puresol.commons.utils.math.ParameterWithArbitraryUnit;

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
