package com.puresol.coding.metrics.sloc;

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
import com.puresol.utils.math.statistics.Statistics;

public class SLOCResults implements MetricResults {

	private static final long serialVersionUID = -3995835459935284595L;

	private final List<SLOCResult> results = new ArrayList<SLOCResult>();

	private final ParameterWithArbitraryUnit<CodeLocation> sourceCodeLocationParameter = SourceCodeLocationParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<CodeRangeType> codeRangeTypeParameter = CodeRangeTypeParameter
			.getInstance();
	private final ParameterWithArbitraryUnit<String> codeRangeNameParameter = CodeRangeNameParameter
			.getInstance();

	private final ParameterWithArbitraryUnit<Integer> phyLOCParameter = new ParameterWithArbitraryUnit<Integer>(
			"phyLOC", "", LevelOfMeasurement.RATIO,
			"Number pf physical lines of code.", Integer.class);
	private final ParameterWithArbitraryUnit<Integer> proLOCParameter = new ParameterWithArbitraryUnit<Integer>(
			"proLOC", "", LevelOfMeasurement.RATIO,
			"Number of productive lines of code.", Integer.class);
	private final ParameterWithArbitraryUnit<Integer> comLOCParameter = new ParameterWithArbitraryUnit<Integer>(
			"comLOC", "", LevelOfMeasurement.RATIO,
			"Number of comments lines of code.", Integer.class);
	private final ParameterWithArbitraryUnit<Integer> blLOCParameter = new ParameterWithArbitraryUnit<Integer>(
			"blLOC", "", LevelOfMeasurement.RATIO,
			"Number of blank lines of code.", Integer.class);

	private final ParameterWithArbitraryUnit<Integer> minParameter = new ParameterWithArbitraryUnit<Integer>(
			"minLineLength", "", LevelOfMeasurement.RATIO,
			"Minimal source code line length.", Integer.class);
	private final ParameterWithArbitraryUnit<Integer> maxParameter = new ParameterWithArbitraryUnit<Integer>(
			"maxLineLength", "", LevelOfMeasurement.RATIO,
			"Maximal source code line length.", Integer.class);
	private final ParameterWithArbitraryUnit<Double> avgParameter = new ParameterWithArbitraryUnit<Double>(
			"avgLineLength", "", LevelOfMeasurement.RATIO,
			"Average of source code line lengths.", Double.class);
	private final ParameterWithArbitraryUnit<Integer> medianParameter = new ParameterWithArbitraryUnit<Integer>(
			"medLineLength", "", LevelOfMeasurement.RATIO,
			"Median of source code line lengths.", Integer.class);
	private final ParameterWithArbitraryUnit<Double> stdDevParameter = new ParameterWithArbitraryUnit<Double>(
			"stdDevLineLength", "", LevelOfMeasurement.RATIO,
			"Standard deviation of source code line length.", Double.class);

	private final ParameterWithArbitraryUnit<SourceCodeQuality> qualityParameter = SourceCodeQualityParameter
			.getInstance();

	public void add(SLOCResult result) {
		results.add(result);
	}

	public List<SLOCResult> getResults() {
		return results;
	}

	@Override
	public List<Parameter<?>> getParameters() {
		List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();
		parameters.add(sourceCodeLocationParameter);
		parameters.add(codeRangeTypeParameter);
		parameters.add(codeRangeNameParameter);
		parameters.add(phyLOCParameter);
		parameters.add(proLOCParameter);
		parameters.add(comLOCParameter);
		parameters.add(blLOCParameter);
		parameters.add(minParameter);
		parameters.add(maxParameter);
		parameters.add(avgParameter);
		parameters.add(medianParameter);
		parameters.add(stdDevParameter);
		parameters.add(qualityParameter);
		return parameters;
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

		for (SLOCResult result : results) {
			SLOCMetric metric = result.getSLOCMetric();
			Statistics stat = metric.getLineStatistics();
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
			row.put(phyLOCParameter.getName(),
					new GeneralValue<Integer>(metric.getPhyLOC(),
							phyLOCParameter));
			row.put(proLOCParameter.getName(),
					new GeneralValue<Integer>(metric.getProLOC(),
							proLOCParameter));
			row.put(comLOCParameter.getName(),
					new GeneralValue<Integer>(metric.getComLOC(),
							comLOCParameter));
			row.put(blLOCParameter.getName(),
					new GeneralValue<Integer>(metric.getBlLOC(), blLOCParameter));
			row.put(minParameter.getName(), new GeneralValue<Integer>(
					(int) stat.getMin(), minParameter));
			row.put(maxParameter.getName(), new GeneralValue<Integer>(
					(int) stat.getMax(), maxParameter));
			row.put(avgParameter.getName(),
					new GeneralValue<Double>(stat.getAvg(), avgParameter));
			Double median = stat.getMedian();
			if (median != null) {
				row.put(medianParameter.getName(), new GeneralValue<Integer>(
						median.intValue(), medianParameter));
			}
			Double stdDev = stat.getStdDev();
			if (stdDev != null) {
				row.put(stdDevParameter.getName(), new GeneralValue<Double>(
						stdDev, stdDevParameter));
			}
			row.put(qualityParameter.getName(),
					new GeneralValue<SourceCodeQuality>(result.getQuality(),
							qualityParameter));
			values.add(row);
		}

		return values;
	}
}
