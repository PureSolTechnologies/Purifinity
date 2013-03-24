package com.puresol.coding.metrics.codedepth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.evaluation.api.CodeRangeNameParameter;
import com.puresol.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.coding.evaluation.api.SourceCodeLocationParameter;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.coding.evaluation.api.SourceCodeQualityParameter;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.math.GeneralValue;
import com.puresol.utils.math.LevelOfMeasurement;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.ParameterWithArbitraryUnit;
import com.puresol.utils.math.Value;

public class CodeDepthResults implements MetricResults {

	private static final long serialVersionUID = 5885874850811986090L;

	private final List<CodeDepthResult> results = new ArrayList<CodeDepthResult>();

	private final ParameterWithArbitraryUnit<CodeLocation> sourceCodeLocationParameter = SourceCodeLocationParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<CodeRangeType> codeRangeTypeParameter = CodeRangeTypeParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<String> codeRangeNameParameter = CodeRangeNameParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<Integer> maxDepthParameter = new ParameterWithArbitraryUnit<Integer>(
			"maxDepth", "", LevelOfMeasurement.RATIO,
			"Maximum nesting depth in evaluated code ranges.", Integer.class);
	private final ParameterWithArbitraryUnit<SourceCodeQuality> qualityParameter = SourceCodeQualityParameter
			.getInstance();

	public void add(CodeDepthResult result) {
		results.add(result);
	}

	@Override
	public List<Parameter<?>> getParameters() {
		List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
		parameters.add(sourceCodeLocationParameter);
		parameters.add(codeRangeTypeParameter);
		parameters.add(codeRangeNameParameter);
		parameters.add(maxDepthParameter);
		parameters.add(qualityParameter);
		return parameters;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (CodeDepthResult result : results) {
			Map<String, Value<?>> row = new HashMap<String, Value<?>>();
			row.put(sourceCodeLocationParameter.getName(),
					new GeneralValue<CodeLocation>(result
							.getSourceCodeLocation(),
							sourceCodeLocationParameter));
			row.put(codeRangeTypeParameter.getName(),
					new GeneralValue<CodeRangeType>(result.getCodeRangeType(),
							codeRangeTypeParameter));
			row.put(codeRangeNameParameter.getName(), new GeneralValue<String>(
					result.getCodeRangeName(), codeRangeNameParameter));
			row.put(maxDepthParameter.getName(), new GeneralValue<Integer>(
					result.getMaxDepth(), maxDepthParameter));
			row.put(qualityParameter.getName(),
					new GeneralValue<SourceCodeQuality>(result.getQuality(),
							qualityParameter));
			values.add(row);
		}

		return values;
	}
}
