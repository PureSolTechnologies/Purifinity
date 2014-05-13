package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.mccabe;

import static com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.mccabe.McCabeMetricEvaluatorParameter.ALL;

import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

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
		SourceCodeQuality quality = SourceCodeQuality.getMinimum(
				left.getQuality(), right.getQuality());
		return new McCabeMetricResult(left.getSourceCodeLocation(),
				left.getCodeRangeType(), left.getCodeRangeName(), vG, quality);
	}

}
