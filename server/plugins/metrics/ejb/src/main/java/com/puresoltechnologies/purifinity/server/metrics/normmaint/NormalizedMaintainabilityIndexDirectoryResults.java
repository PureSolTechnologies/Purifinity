package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.QUALITY_LEVEL;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class NormalizedMaintainabilityIndexDirectoryResults extends
		AbstractMetrics implements DirectoryMetrics {

	private static final long serialVersionUID = -5901342878584699006L;

	private final HashId hashId;

	public NormalizedMaintainabilityIndexDirectoryResults(String evaluatorId,
			HashId hashId, Date time) {
		super(evaluatorId, time);
		this.hashId = hashId;
	}

	@Override
	public HashId getHashId() {
		return hashId;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public Map<String, MetricValue<?>> getValues() {
		Map<String, MetricValue<?>> values = new HashMap<>();
		values.put(QUALITY.getName(), new MetricValue<SourceCodeQuality>(
				getSourceQuality(), QUALITY));
		values.put(QUALITY_LEVEL.getName(), new MetricValue<QualityLevel>(
				getQualityLevel(), QUALITY_LEVEL));
		return values;
	}
}
