package com.puresol.coding.metrics.maintainability;

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

	public static final List<Parameter<?>> ALL = new ArrayList<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
		ALL.add(MI_WOC);
		ALL.add(MI_CW);
		ALL.add(MI);
		ALL.add(QUALITY);
	}
}
