package com.puresol.coding.metrics.normmaint;

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

public class NormalizedMaintainabilityIndexEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<CodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<Double> NORM_MI_WOC = new ParameterWithArbitraryUnit<Double>(
			"nMIwoc", "", LevelOfMeasurement.ORDINAL,
			"Normalized maintainability index without comments", Double.class);
	public static final ParameterWithArbitraryUnit<Double> NORM_MI_CW = new ParameterWithArbitraryUnit<Double>(
			"nMIcw", "", LevelOfMeasurement.ORDINAL,
			"Normalized maintainability index comment weight", Double.class);
	public static final ParameterWithArbitraryUnit<Double> NORM_MI = new ParameterWithArbitraryUnit<Double>(
			"nMI", "", LevelOfMeasurement.ORDINAL,
			"Normalized maintainability index including comments", Double.class);
	public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
			.getInstance();

	public static final Set<Parameter<?>> ALL = new HashSet<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
		ALL.add(NORM_MI_WOC);
		ALL.add(NORM_MI_CW);
		ALL.add(NORM_MI);
		ALL.add(QUALITY);
	}
}
