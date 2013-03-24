package com.puresol.coding.evaluation.api;

import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.ParameterWithArbitraryUnit;

public class SourceCodeQualityParameter extends
		ParameterWithArbitraryUnit<SourceCodeQuality> {

	private static final long serialVersionUID = -931199455057090001L;

	private static final SourceCodeQualityParameter INSTANCE = new SourceCodeQualityParameter();

	public static SourceCodeQualityParameter getInstance() {
		return INSTANCE;
	}

	private SourceCodeQualityParameter() {
		super("source code quality", "", LevelOfMeasurement.ORDINAL,
				"Quality measure of related source code range.",
				SourceCodeQuality.class);
	}

}
