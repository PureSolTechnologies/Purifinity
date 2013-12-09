package com.puresoltechnologies.purifinity.coding.metrics.maintainability;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevelParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQualityParameter;

public class MaintainabilityIndexEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<CodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<Double> MI_WOC = new ParameterWithArbitraryUnit<Double>(
			"MIwoc", "", LevelOfMeasurement.ORDINAL,
			"Maintainability index without comments", Double.class);
	public static final ParameterWithArbitraryUnit<Double> MI_CW = new ParameterWithArbitraryUnit<Double>(
			"MIcw", "", LevelOfMeasurement.ORDINAL,
			"Maintainability index comment weight", Double.class);
	public static final ParameterWithArbitraryUnit<Double> MI = new ParameterWithArbitraryUnit<Double>(
			"MI", "", LevelOfMeasurement.ORDINAL,
			"Maintainability index including comments", Double.class);
	public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
			.getInstance();

	public static final Set<Parameter<?>> ALL_FILE = new HashSet<Parameter<?>>();
	static {
		ALL_FILE.add(SOURCE_CODE_LOCATION);
		ALL_FILE.add(CODE_RANGE_TYPE);
		ALL_FILE.add(CODE_RANGE_NAME);
		ALL_FILE.add(MI_WOC);
		ALL_FILE.add(MI_CW);
		ALL_FILE.add(MI);
		ALL_FILE.add(QUALITY);
		ALL_FILE.add(QUALITY_LEVEL);
	}

	public static final Set<Parameter<?>> ALL_DIRECTORY = new HashSet<Parameter<?>>();
	static {
		ALL_DIRECTORY.add(SOURCE_CODE_LOCATION);
		ALL_DIRECTORY.add(CODE_RANGE_TYPE);
		ALL_DIRECTORY.add(CODE_RANGE_NAME);
		ALL_DIRECTORY.add(QUALITY);
		ALL_DIRECTORY.add(QUALITY_LEVEL);
	}
}
