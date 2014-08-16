package com.puresoltechnologies.purifinity.server.metrics.halstead;

import static com.puresoltechnologies.purifinity.server.metrics.halstead.HalsteadMetricEvaluatorParameter.ALL;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class HalsteadMetricDirectoryResults extends AbstractHalsteadResults
		implements DirectoryMetrics {

	private static final long serialVersionUID = -5970030495863471269L;

	private final HashId hashId;
	private final HalsteadMetricResult result;

	public HalsteadMetricDirectoryResults(String evaluatorId, HashId hashId,
			Date time, HalsteadMetricResult result) {
		super(evaluatorId, time);
		this.hashId = hashId;
		if (result == null) {
			throw new IllegalArgumentException("Result must not be null!");
		}
		this.result = result;
	}

	@Override
	public HashId getHashId() {
		return hashId;
	}

	public HalsteadMetricResult getResult() {
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
}
