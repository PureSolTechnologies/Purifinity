package com.puresol.purifinity.coding.evaluation.api;

import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.utils.math.LevelOfMeasurement;
import com.puresol.purifinity.utils.math.ParameterWithArbitraryUnit;

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
