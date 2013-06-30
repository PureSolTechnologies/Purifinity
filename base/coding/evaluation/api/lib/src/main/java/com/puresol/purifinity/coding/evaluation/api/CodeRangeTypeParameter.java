package com.puresol.purifinity.coding.evaluation.api;

import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.utils.math.LevelOfMeasurement;
import com.puresol.purifinity.utils.math.ParameterWithArbitraryUnit;

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
