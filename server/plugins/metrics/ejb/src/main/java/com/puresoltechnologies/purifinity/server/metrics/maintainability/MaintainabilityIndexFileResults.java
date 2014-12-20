package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_CW;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.MI_WOC;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.server.metrics.maintainability.MaintainabilityIndexEvaluatorParameter.QUALITY_LEVEL;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class MaintainabilityIndexFileResults extends AbstractMetrics implements
		FileMetrics {

	private static final long serialVersionUID = -5901342878584699006L;

	private final List<MaintainabilityIndexFileResult> results = new ArrayList<MaintainabilityIndexFileResult>();

	private final HashId hashId;
	private final SourceCodeLocation sourceCodeLocation;

	public MaintainabilityIndexFileResults(String evaluatorId, HashId hashId,
			SourceCodeLocation sourceCodeLocation, Date time) {
		super(evaluatorId, time);
		this.hashId = hashId;
		this.sourceCodeLocation = sourceCodeLocation;
	}

	@Override
	public HashId getHashId() {
		return hashId;
	}

	@Override
	public SourceCodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public void add(MaintainabilityIndexFileResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	public List<MaintainabilityIndexFileResult> getResults() {
		return results;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<GenericCodeRangeMetrics> getValues() {
		List<GenericCodeRangeMetrics> values = new ArrayList<>();

		for (MaintainabilityIndexFileResult result : results) {
			MaintainabilityIndexResult mi = result
					.getMaintainabilityIndexResult();
			Map<String, MetricValue<?>> row = new HashMap<>();
			row.put(MI_WOC.getName(), new MetricValue<Double>(mi.getMIwoc(),
					MI_WOC));
			row.put(MI_CW.getName(), new MetricValue<Double>(mi.getMIcw(),
					MI_CW));
			row.put(MI.getName(), new MetricValue<Double>(mi.getMI(), MI));
			SourceCodeQuality quality = result.getQuality();
			row.put(QUALITY.getName(), new MetricValue<SourceCodeQuality>(
					quality, QUALITY));
			if (quality != SourceCodeQuality.UNSPECIFIED) {
				row.put(QUALITY_LEVEL.getName(), new MetricValue<QualityLevel>(
						new QualityLevel(quality), QUALITY_LEVEL));
			}
			values.add(new GenericCodeRangeMetrics(result
					.getSourceCodeLocation(), result.getCodeRangeType(), result
					.getCodeRangeName(),
					MaintainabilityIndexEvaluatorParameter.ALL, row));
		}
		return values;
	}
}
