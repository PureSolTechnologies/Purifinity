package com.puresol.coding.metrics.entropy;

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

public class EntropyResults implements MetricResults {

	private static final long serialVersionUID = 4585034044953318000L;

	private final List<EntropyResult> results = new ArrayList<EntropyResult>();

	private final ParameterWithArbitraryUnit<CodeLocation> sourceCodeLocationParameter = SourceCodeLocationParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<CodeRangeType> codeRangeTypeParameter = CodeRangeTypeParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<String> codeRangeNameParameter = CodeRangeNameParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<Integer> nDiffParameter = new ParameterWithArbitraryUnit<Integer>(
			"nDiff", "", LevelOfMeasurement.RATIO,
			"Number of different operators", Integer.class);
	private final ParameterWithArbitraryUnit<Integer> nTotalParameter = new ParameterWithArbitraryUnit<Integer>(
			"nTotal", "", LevelOfMeasurement.RATIO,
			"Total number of operators", Integer.class);
	private final ParameterWithArbitraryUnit<Double> sParameter = new ParameterWithArbitraryUnit<Double>(
			"S", "", LevelOfMeasurement.RATIO, "Entropy", Double.class);
	private final ParameterWithArbitraryUnit<Double> sMaxParameter = new ParameterWithArbitraryUnit<Double>(
			"Smax", "", LevelOfMeasurement.RATIO, "Maximum entropy",
			Double.class);
	private final ParameterWithArbitraryUnit<Double> sNormParameter = new ParameterWithArbitraryUnit<Double>(
			"Snorm", "", LevelOfMeasurement.RATIO, "Normalized entropy",
			Double.class);
	private final ParameterWithArbitraryUnit<Double> rsParameter = new ParameterWithArbitraryUnit<Double>(
			"Rs", "", LevelOfMeasurement.RATIO, "Entropic redundancy",
			Double.class);
	private final ParameterWithArbitraryUnit<Double> rParameter = new ParameterWithArbitraryUnit<Double>(
			"R", "", LevelOfMeasurement.RATIO, "Redundancy", Double.class);
	private final ParameterWithArbitraryUnit<Double> rnormParameter = new ParameterWithArbitraryUnit<Double>(
			"Rnorm", "", LevelOfMeasurement.RATIO, "Normalized redundancy",
			Double.class);
	private final ParameterWithArbitraryUnit<SourceCodeQuality> qualityParameter = SourceCodeQualityParameter
			.getInstance();

	public void add(EntropyResult result) {
		results.add(result);
	}

	@Override
	public List<Parameter<?>> getParameters() {
		List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
		parameters.add(sourceCodeLocationParameter);
		parameters.add(codeRangeTypeParameter);
		parameters.add(codeRangeNameParameter);
		parameters.add(nDiffParameter);
		parameters.add(nTotalParameter);
		parameters.add(sParameter);
		parameters.add(sMaxParameter);
		parameters.add(sNormParameter);
		parameters.add(rsParameter);
		parameters.add(rParameter);
		parameters.add(rnormParameter);
		parameters.add(qualityParameter);
		return parameters;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (EntropyResult result : results) {
			EntropyMetricResult entropy = result.getEntropyMetricResult();
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
			row.put(nDiffParameter.getName(),
					new GeneralValue<Integer>(entropy.getNDiff(),
							nDiffParameter));

			row.put(nTotalParameter.getName(), new GeneralValue<Integer>(
					entropy.getNTotal(), nTotalParameter));
			row.put(sParameter.getName(),
					new GeneralValue<Double>(entropy.getEntropy(), sParameter));
			row.put(sMaxParameter.getName(),
					new GeneralValue<Double>(entropy.getMaxEntropy(),
							sMaxParameter));
			row.put(sNormParameter.getName(),
					new GeneralValue<Double>(entropy.getNormEntropy(),
							sNormParameter));
			row.put(rsParameter.getName(),
					new GeneralValue<Double>(entropy.getEntropyRedundancy(),
							rsParameter));
			row.put(rParameter.getName(),
					new GeneralValue<Double>(entropy.getRedundancy(),
							rParameter));
			row.put(rnormParameter.getName(),
					new GeneralValue<Double>(entropy.getNormalizedRedundancy(),
							rnormParameter));
			row.put(qualityParameter.getName(),
					new GeneralValue<SourceCodeQuality>(result.getQuality(),
							qualityParameter));
			values.add(row);
		}

		return values;
	}
}
