package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.List;

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

	public static final List<Parameter<?>> PARAMETERS = new ArrayList<Parameter<?>>();
	static {
		PARAMETERS.add(SOURCE_CODE_LOCATION);
		PARAMETERS.add(CODE_RANGE_TYPE);
		PARAMETERS.add(CODE_RANGE_NAME);
		PARAMETERS.add(MAX_DEPTH);
		PARAMETERS.add(QUALITY);
	}
}
