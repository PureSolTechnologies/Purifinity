package com.puresoltechnologies.purifinity.framework.evaluation.metrics.entropy;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevelParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQualityParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class EntropyMetricEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<SourceCodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
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
	public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
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
		ALL.add(QUALITY_LEVEL);
	}
}
