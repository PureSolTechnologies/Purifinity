package com.puresol.purifinity.coding.evaluation.api;

import com.puresol.commons.math.LevelOfMeasurement;
import com.puresol.commons.math.ParameterWithArbitraryUnit;
import com.puresol.purifinity.uhura.source.CodeLocation;

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
