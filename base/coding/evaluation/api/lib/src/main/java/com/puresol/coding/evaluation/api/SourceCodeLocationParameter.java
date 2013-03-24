package com.puresol.coding.evaluation.api;

import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.ParameterWithArbitraryUnit;

public class SourceCodeLocationParameter extends
		ParameterWithArbitraryUnit<CodeLocation> {

	private static final long serialVersionUID = -931199455057090001L;

	private static final SourceCodeLocationParameter INSTANCE = new SourceCodeLocationParameter();

	public static SourceCodeLocationParameter getInstance() {
		return INSTANCE;
	}

	private SourceCodeLocationParameter() {
		super("Source Code Location", "", LevelOfMeasurement.NOMINAL,
				"Location of source code.", CodeLocation.class);
	}

}
