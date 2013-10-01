package com.puresol.purifinity.coding.metrics.mccabe;

import java.util.HashSet;
import java.util.Set;

import com.puresol.commons.math.LevelOfMeasurement;
import com.puresol.commons.math.Parameter;
import com.puresol.commons.math.ParameterWithArbitraryUnit;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;
import com.puresol.purifinity.coding.evaluation.api.QualityLevelParameter;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.purifinity.uhura.source.CodeLocation;

public class McCabeMetricEvaluatorParameter {

	public static final ParameterWithArbitraryUnit<CodeLocation> SOURCE_CODE_LOCATION = SourceCodeLocationParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<CodeRangeType> CODE_RANGE_TYPE = CodeRangeTypeParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<String> CODE_RANGE_NAME = CodeRangeNameParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<Integer> VG = new ParameterWithArbitraryUnit<Integer>(
			"v(G)", "", LevelOfMeasurement.RATIO,
			"McCabe's cyclomatic complexity for evaluated code range.",
			Integer.class);
	public static final ParameterWithArbitraryUnit<SourceCodeQuality> QUALITY = SourceCodeQualityParameter
			.getInstance();
	public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
			.getInstance();

	public static final Set<Parameter<?>> ALL = new HashSet<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
		ALL.add(VG);
		ALL.add(QUALITY);
		ALL.add(QUALITY_LEVEL);
	}
}
