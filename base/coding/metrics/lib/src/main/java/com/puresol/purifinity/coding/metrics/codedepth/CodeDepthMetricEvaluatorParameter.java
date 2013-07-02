package com.puresol.purifinity.coding.metrics.codedepth;

import java.util.HashSet;
import java.util.Set;

import com.puresol.commons.utils.math.LevelOfMeasurement;
import com.puresol.commons.utils.math.Parameter;
import com.puresol.commons.utils.math.ParameterWithArbitraryUnit;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class CodeDepthMetricEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<CodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<Integer> MAX_DEPTH = new ParameterWithArbitraryUnit<Integer>(
			"maxDepth", "", LevelOfMeasurement.RATIO,
			"Maximum nesting depth in evaluated code ranges.", Integer.class);
	public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
			.getInstance();

	public static final Set<Parameter<?>> ALL = new HashSet<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
		ALL.add(MAX_DEPTH);
		ALL.add(QUALITY);
	}
}
