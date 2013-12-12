package com.puresoltechnologies.purifinity.coding.metrics.codedepth;

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
	public static final ParameterWithArbitraryUnit<QualityLevel> QUALITY_LEVEL = QualityLevelParameter
			.getInstance();

	public static final Set<Parameter<?>> ALL = new HashSet<Parameter<?>>();
	static {
		ALL.add(SOURCE_CODE_LOCATION);
		ALL.add(CODE_RANGE_TYPE);
		ALL.add(CODE_RANGE_NAME);
		ALL.add(MAX_DEPTH);
		ALL.add(QUALITY);
		ALL.add(QUALITY_LEVEL);
	}
}
