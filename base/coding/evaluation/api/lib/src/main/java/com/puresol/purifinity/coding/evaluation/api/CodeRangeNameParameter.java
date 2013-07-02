package com.puresol.purifinity.coding.evaluation.api;

import com.puresol.commons.utils.math.LevelOfMeasurement;
import com.puresol.commons.utils.math.ParameterWithArbitraryUnit;

public class CodeRangeNameParameter extends ParameterWithArbitraryUnit<String> {

	private static final long serialVersionUID = -3502947307793097432L;

	private static final CodeRangeNameParameter INSTANCE = new CodeRangeNameParameter();

	public static CodeRangeNameParameter getInstance() {
		return INSTANCE;
	}

	private CodeRangeNameParameter() {
		super("Name of Code Range", "", LevelOfMeasurement.NOMINAL,
				"The name of the evaluated code range.", String.class);
	}

}
