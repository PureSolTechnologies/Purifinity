package com.puresol.purifinity.coding.metrics.maintainability;

import java.util.HashSet;
import java.util.Set;

import com.puresol.commons.utils.math.LevelOfMeasurement;
import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.ParameterWithArbitraryUnit;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.QualityLevelParameter;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.purifinity.uhura.source.CodeLocation;

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
