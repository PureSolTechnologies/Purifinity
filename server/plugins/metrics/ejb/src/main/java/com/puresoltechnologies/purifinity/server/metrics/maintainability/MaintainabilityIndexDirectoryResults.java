package com.puresoltechnologies.purifinity.server.metrics.maintainability;

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

public class MaintainabilityIndexDirectoryResults extends AbstractMetrics
		implements DirectoryMetrics {

	private static final long serialVersionUID = -5901342878584699006L;

	private final HashId hashId;

	public MaintainabilityIndexDirectoryResults(String evaluatorId,
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
		Map<String, MetricValue<?>> row = new HashMap<>();
		row.put(QUALITY.getName(), new MetricValue<SourceCodeQuality>(
				getSourceQuality(), QUALITY));
		row.put(QUALITY_LEVEL.getName(), new MetricValue<QualityLevel>(
				getQualityLevel(), QUALITY_LEVEL));
		return row;
	}
}
