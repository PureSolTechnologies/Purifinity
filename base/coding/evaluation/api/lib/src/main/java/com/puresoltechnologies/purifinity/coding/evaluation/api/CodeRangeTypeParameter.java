package com.puresoltechnologies.purifinity.coding.evaluation.api;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;

public class CodeRangeTypeParameter extends
		ParameterWithArbitraryUnit<CodeRangeType> {

	private static final long serialVersionUID = 8065733183022225295L;

	private static final CodeRangeTypeParameter INSTANCE = new CodeRangeTypeParameter();

	public static CodeRangeTypeParameter getInstance() {
		return INSTANCE;
	}

	private CodeRangeTypeParameter() {
		super("Type of Code Range", "", LevelOfMeasurement.NOMINAL,
				"Type of evaluated code range.", CodeRangeType.class);
	}

}
