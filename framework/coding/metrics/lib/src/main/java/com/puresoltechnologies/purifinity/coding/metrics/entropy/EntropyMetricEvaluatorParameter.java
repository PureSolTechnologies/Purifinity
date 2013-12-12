package com.puresoltechnologies.purifinity.coding.metrics.entropy;

import java.util.HashSet;
import java.util.Set;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
<<<<<<< HEAD
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.api.QualityLevelParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQualityParameter;
=======
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevel;
import com.puresoltechnologies.purifinity.coding.evaluation.api.QualityLevelParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresoltechnologies.purifinity.coding.evaluation.api.SourceCodeQualityParameter;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9

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
