package com.puresol.coding.metrics.halstead;

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

public class HalsteadMetricResults implements MetricResults {

	private static final long serialVersionUID = -5970030495863471269L;

	private final List<HalsteadMetricResult> results = new ArrayList<HalsteadMetricResult>();

	private final ParameterWithArbitraryUnit<CodeLocation> sourceCodeLocationParameter = SourceCodeLocationParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<CodeRangeType> codeRangeTypeParameter = CodeRangeTypeParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<String> codeRangeNameParameter = CodeRangeNameParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<Integer> differentOperatorsParameter = new ParameterWithArbitraryUnit<Integer>(
			"n1", "", LevelOfMeasurement.RATIO, "Number of unique operators",
			Integer.class);
	private final ParameterWithArbitraryUnit<Integer> differentOperandsParameter = new ParameterWithArbitraryUnit<Integer>(
			"n2", "", LevelOfMeasurement.RATIO, "Number of unique operands",
			Integer.class);
	private final ParameterWithArbitraryUnit<Integer> totalOperatorsParameter = new ParameterWithArbitraryUnit<Integer>(
			"N1", "", LevelOfMeasurement.RATIO, "Total number of operators",
			Integer.class);
	private final ParameterWithArbitraryUnit<Integer> totalOperandsParameter = new ParameterWithArbitraryUnit<Integer>(
			"N2", "", LevelOfMeasurement.RATIO, "Total number of operands",
			Integer.class);
	private final ParameterWithArbitraryUnit<Integer> vocabularySizeParameter = new ParameterWithArbitraryUnit<Integer>(
			"N", "", LevelOfMeasurement.RATIO, "Program length", Integer.class);
	private final ParameterWithArbitraryUnit<Integer> programLengthParameter = new ParameterWithArbitraryUnit<Integer>(
			"n", "", LevelOfMeasurement.RATIO, "Vocabulary Size", Integer.class);
	private final ParameterWithArbitraryUnit<Double> halsteadLengthParameter = new ParameterWithArbitraryUnit<Double>(
			"V", "", LevelOfMeasurement.RATIO, "Halstead volume", Double.class);
	private final ParameterWithArbitraryUnit<Double> halsteadVolumeParameter = new ParameterWithArbitraryUnit<Double>(
			"Hl", "", LevelOfMeasurement.RATIO, "Halstead length", Double.class);
	private final ParameterWithArbitraryUnit<Double> difficultyParameter = new ParameterWithArbitraryUnit<Double>(
			"D", "", LevelOfMeasurement.RATIO, "Difficulty", Double.class);
	private final ParameterWithArbitraryUnit<Double> programLevelParameter = new ParameterWithArbitraryUnit<Double>(
			"L", "", LevelOfMeasurement.RATIO, "Program level", Double.class);
	private final ParameterWithArbitraryUnit<Double> implementationEffortParameter = new ParameterWithArbitraryUnit<Double>(
			"E", "", LevelOfMeasurement.RATIO, "Implementation effort",
			Double.class);
	private final ParameterWithArbitraryUnit<Double> implementationTimeParameter = new ParameterWithArbitraryUnit<Double>(
			"T", "Seconds", LevelOfMeasurement.RATIO, "Implementation time",
			Double.class);
	private final ParameterWithArbitraryUnit<Double> estimatedBugsParameter = new ParameterWithArbitraryUnit<Double>(
			"B", "", LevelOfMeasurement.RATIO, "Number of delivered bugs",
			Double.class);
	private final ParameterWithArbitraryUnit<SourceCodeQuality> qualityParameter = SourceCodeQualityParameter
			.getInstance();

	public void add(HalsteadMetricResult result) {
		results.add(result);
	}

	public List<HalsteadMetricResult> getResults() {
		return results;
	}

	@Override
	public List<Parameter<?>> getParameters() {
		List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
		parameters.add(sourceCodeLocationParameter);
		parameters.add(codeRangeTypeParameter);
		parameters.add(codeRangeNameParameter);
		parameters.add(differentOperatorsParameter);
		parameters.add(differentOperandsParameter);
		parameters.add(totalOperatorsParameter);
		parameters.add(totalOperandsParameter);
		parameters.add(vocabularySizeParameter);
		parameters.add(programLengthParameter);
		parameters.add(halsteadLengthParameter);
		parameters.add(halsteadVolumeParameter);
		parameters.add(difficultyParameter);
		parameters.add(programLevelParameter);
		parameters.add(implementationEffortParameter);
		parameters.add(implementationTimeParameter);
		parameters.add(estimatedBugsParameter);
		parameters.add(qualityParameter);
		return parameters;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (HalsteadMetricResult result : results) {
			HalsteadResult halstead = result.getHalsteadResult();
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
			row.put(differentOperatorsParameter.getName(),
					new GeneralValue<Integer>(halstead.getDifferentOperators(),
							differentOperatorsParameter));
			row.put(differentOperandsParameter.getName(),
					new GeneralValue<Integer>(halstead.getDifferentOperands(),
							differentOperandsParameter));
			row.put(totalOperatorsParameter.getName(),
					new GeneralValue<Integer>(halstead.getTotalOperators(),
							totalOperatorsParameter));
			row.put(totalOperandsParameter.getName(),
					new GeneralValue<Integer>(halstead.getTotalOperands(),
							totalOperandsParameter));
			row.put(vocabularySizeParameter.getName(),
					new GeneralValue<Integer>(halstead.getVocabularySize(),
							vocabularySizeParameter));
			row.put(programLengthParameter.getName(),
					new GeneralValue<Integer>(halstead.getProgramLength(),
							programLengthParameter));
			row.put(halsteadLengthParameter.getName(),
					new GeneralValue<Double>(halstead.getHalsteadLength(),
							halsteadLengthParameter));
			row.put(halsteadVolumeParameter.getName(),
					new GeneralValue<Double>(halstead.getHalsteadVolume(),
							halsteadVolumeParameter));
			row.put(difficultyParameter.getName(), new GeneralValue<Double>(
					halstead.getDifficulty(), difficultyParameter));
			row.put(programLevelParameter.getName(), new GeneralValue<Double>(
					halstead.getProgramLevel(), programLevelParameter));
			row.put(implementationEffortParameter.getName(),
					new GeneralValue<Double>(
							halstead.getImplementationEffort(),
							implementationEffortParameter));
			row.put(implementationTimeParameter.getName(),
					new GeneralValue<Double>(halstead.getImplementationTime(),
							implementationTimeParameter));
			row.put(estimatedBugsParameter.getName(), new GeneralValue<Double>(
					halstead.getEstimatedBugs(), estimatedBugsParameter));
			row.put(qualityParameter.getName(),
					new GeneralValue<SourceCodeQuality>(result.getQuality(),
							qualityParameter));
			values.add(row);
		}

		return values;
	}
}
