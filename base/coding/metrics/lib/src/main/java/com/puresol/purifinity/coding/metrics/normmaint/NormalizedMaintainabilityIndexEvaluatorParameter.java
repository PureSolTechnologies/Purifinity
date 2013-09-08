package com.puresol.purifinity.coding.metrics.normmaint;

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
	public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
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
		ALL.add(QUALITY_LEVEL);
	}
}
