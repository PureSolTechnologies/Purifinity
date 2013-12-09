package com.puresoltechnologies.purifinity.coding.metrics.normmaint;

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
