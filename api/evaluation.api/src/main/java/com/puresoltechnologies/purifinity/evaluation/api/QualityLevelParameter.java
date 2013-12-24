package com.puresoltechnologies.purifinity.evaluation.api;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;

public class QualityLevelParameter extends
		ParameterWithArbitraryUnit<QualityLevel> {

	private static final long serialVersionUID = -931199455057090001L;

	public static final String NAME = "quality level";

	private static final QualityLevelParameter INSTANCE = new QualityLevelParameter();

	public static QualityLevelParameter getInstance() {
		return INSTANCE;
	}

	private QualityLevelParameter() {
		super(
				NAME,
				"",
				LevelOfMeasurement.INTERVAL,
				"Quality level of file or directory. This is an aggregate parameter of all sub folders and their containing files.",
				QualityLevel.class);
	}

}
