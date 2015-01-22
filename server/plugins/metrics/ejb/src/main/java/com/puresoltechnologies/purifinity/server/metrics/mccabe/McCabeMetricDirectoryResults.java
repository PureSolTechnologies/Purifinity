package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import static com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluatorParameter.ALL;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQualityParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class McCabeMetricDirectoryResults extends AbstractMcCabeMetricResults
		implements DirectoryMetrics {

	private static final long serialVersionUID = -5992363758018121695L;

	private final HashId hashId;
	private final McCabeMetricResult result;

	public McCabeMetricDirectoryResults(String evaluatorId, HashId hashId,
			Date time, McCabeMetricResult result) {
		super(evaluatorId, time);
		this.hashId = hashId;
		this.result = result;
	}

	@Override
	public HashId getHashId() {
		return hashId;
	}

	public McCabeMetricResult getResult() {
		return result;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, MetricValue<?>> getValues() {
		return convertToRow(result);
	}

	public static McCabeMetricResult combine(McCabeMetricResult left,
			GenericCodeRangeMetrics right) {
		int vG = left.getCyclomaticComplexity()
				+ right.getValue(McCabeMetricEvaluatorParameter.VG).getValue();
		SourceCodeQuality quality = SourceCodeQuality.getMinimum(left
				.getQuality(),
				right.getValue(SourceCodeQualityParameter.getInstance())
						.getValue());
		return new McCabeMetricResult(left.getSourceCodeLocation(),
				left.getCodeRangeType(), left.getCodeRangeName(), vG, quality);
	}

	public static McCabeMetricResult combine(McCabeMetricResult left,
			GenericDirectoryMetrics right) {
		int vG = left.getCyclomaticComplexity()
				+ right.getValue(McCabeMetricEvaluatorParameter.VG).getValue();
		SourceCodeQuality quality = SourceCodeQuality.getMinimum(left
				.getQuality(),
				right.getValue(SourceCodeQualityParameter.getInstance())
						.getValue());
		return new McCabeMetricResult(left.getSourceCodeLocation(),
				left.getCodeRangeType(), left.getCodeRangeName(), vG, quality);
	}

}
