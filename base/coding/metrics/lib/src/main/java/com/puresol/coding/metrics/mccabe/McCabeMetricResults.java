package com.puresol.coding.metrics.mccabe;

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

public class McCabeMetricResults implements MetricResults {

	private final List<McCabeMetricResult> results = new ArrayList<McCabeMetricResult>();

	private static final long serialVersionUID = -5992363758018121695L;

	private final ParameterWithArbitraryUnit<CodeLocation> sourceCodeLocationParameter = SourceCodeLocationParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<CodeRangeType> codeRangeTypeParameter = CodeRangeTypeParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<String> codeRangeNameParameter = CodeRangeNameParameter
			.getInstance();

	private final ParameterWithArbitraryUnit<Integer> vGParameter = new ParameterWithArbitraryUnit<Integer>(
			"v(G)", "", LevelOfMeasurement.RATIO,
			"McCabe's cyclomatic complexity for evaluated code range.",
			Integer.class);
	private final ParameterWithArbitraryUnit<SourceCodeQuality> qualityParameter = SourceCodeQualityParameter
			.getInstance();

	public List<McCabeMetricResult> getResults() {
		return results;
	}

	public void add(McCabeMetricResult result) {
		results.add(result);
	}

	@Override
	public List<Parameter<?>> getParameters() {
		List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
		parameters.add(sourceCodeLocationParameter);
		parameters.add(codeRangeTypeParameter);
		parameters.add(codeRangeNameParameter);
		parameters.add(vGParameter);
		parameters.add(qualityParameter);
		return parameters;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (McCabeMetricResult result : results) {
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
			row.put(vGParameter.getName(),
					new GeneralValue<Integer>(result.getCyclomaticComplexity(),
							vGParameter));
			row.put(qualityParameter.getName(),
					new GeneralValue<SourceCodeQuality>(result.getQuality(),
							qualityParameter));
			values.add(row);
		}

		return values;
	}
}
