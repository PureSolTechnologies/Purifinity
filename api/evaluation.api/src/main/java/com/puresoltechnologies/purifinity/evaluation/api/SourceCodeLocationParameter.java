package com.puresoltechnologies.purifinity.evaluation.api;

import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public class SourceCodeLocationParameter extends
		ParameterWithArbitraryUnit<SourceCodeLocation> {

	private static final long serialVersionUID = -931199455057090001L;

	private static final SourceCodeLocationParameter INSTANCE = new SourceCodeLocationParameter();

	public static SourceCodeLocationParameter getInstance() {
		return INSTANCE;
	}

	private SourceCodeLocationParameter() {
		super("Source Code Location", "", LevelOfMeasurement.NOMINAL,
				"Location of source code.", SourceCodeLocation.class);
	}

}
