package com.puresol.coding.metrics.mccabe;

import static com.puresol.coding.metrics.mccabe.McCabeMetricEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresol.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.coding.evaluation.api.SourceCodeQuality;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class McCabeMetricDirectoryResults extends AbstractMcCabeMetricResults
		implements MetricDirectoryResults {

	private final McCabeMetricResult result;

	public McCabeMetricDirectoryResults(McCabeMetricResult result) {
		this.result = result;
	}

	private static final long serialVersionUID = -5992363758018121695L;

	public McCabeMetricResult getResult() {
		return result;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, Value<?>> getValues() {
		return convertToRow(result);
	}

	public static McCabeMetricResult combine(McCabeMetricResult left,
			McCabeMetricResult right) {
		int vG = left.getCyclomaticComplexity()
				+ right.getCyclomaticComplexity();
		SourceCodeQuality quality = SourceCodeQuality.getMinLevel(
				left.getQuality(), right.getQuality());
		return new McCabeMetricResult(left.getSourceCodeLocation(),
				left.getCodeRangeType(), left.getCodeRangeName(), vG, quality);
	}

}
