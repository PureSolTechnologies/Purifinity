package com.puresol.coding.metrics.entropy;

import java.util.HashSet;
import java.util.Set;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;

public class EntropyMetricEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<CodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<Integer> N_DIFF = new ParameterWithArbitraryUnit<Integer>(
			"nDiff", "", LevelOfMeasurement.RATIO,
			"Number of different operators", Integer.class);
	public static final ParameterWithArbitraryUnit<Integer> N_TOTAL = new ParameterWithArbitraryUnit<Integer>(
			"nTotal", "", LevelOfMeasurement.RATIO,
			"Total number of operators", Integer.class);
	public static final ParameterWithArbitraryUnit<Double> S = new ParameterWithArbitraryUnit<Double>(
			"S", "", LevelOfMeasurement.RATIO, "Entropy", Double.class);
	public static final ParameterWithArbitraryUnit<Double> S_MAX = new ParameterWithArbitraryUnit<Double>(
			"Smax", "", LevelOfMeasurement.RATIO, "Maximum entropy",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> S_NORM = new ParameterWithArbitraryUnit<Double>(
			"Snorm", "", LevelOfMeasurement.RATIO, "Normalized entropy",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> RS = new ParameterWithArbitraryUnit<Double>(
			"Rs", "", LevelOfMeasurement.RATIO, "Entropic redundancy",
			Double.class);
	public static final ParameterWithArbitraryUnit<Double> R = new ParameterWithArbitraryUnit<Double>(
			"R", "", LevelOfMeasurement.RATIO, "Redundancy", Double.class);
	public static final ParameterWithArbitraryUnit<Double> R_NORM = new ParameterWithArbitraryUnit<Double>(
			"Rnorm", "", LevelOfMeasurement.RATIO, "Normalized redundancy",
			Double.class);
	public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
			.getInstance();

	public static final Set<Parameter<?>> ALL = new HashSet<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
		ALL.add(N_DIFF);
		ALL.add(N_TOTAL);
		ALL.add(S);
		ALL.add(S_MAX);
		ALL.add(S_NORM);
		ALL.add(RS);
		ALL.add(R);
		ALL.add(R_NORM);
		ALL.add(QUALITY);
	}
}
