package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.ALL;
import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI;
import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI_CW;
import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.NORM_MI_WOC;
import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.server.metrics.normmaint.NormalizedMaintainabilityIndexEvaluatorParameter.QUALITY_LEVEL;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericCodeRangeMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public class NormalizedMaintainabilityIndexFileResults extends AbstractMetrics
		implements FileMetrics {

	private static final long serialVersionUID = 7667134885288322378L;

	private final List<NormalizedMaintainabilityIndexFileResult> results = new ArrayList<NormalizedMaintainabilityIndexFileResult>();

	private final HashId hashId;
	private final SourceCodeLocation sourceCodeLocation;

	public NormalizedMaintainabilityIndexFileResults(String evaluatorId,
			HashId hashId, SourceCodeLocation sourceCodeLocation, Date time) {
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

	public void add(NormalizedMaintainabilityIndexFileResult result) {
		results.add(result);
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(new QualityLevel(result.getQuality()));
		} else {
			qualityLevel.add(result.getQuality());
		}
	}

	public List<NormalizedMaintainabilityIndexFileResult> getResults() {
		return results;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return ALL;
	}

	@Override
	public List<GenericCodeRangeMetrics> getValues() {
		List<GenericCodeRangeMetrics> values = new ArrayList<>();
		for (NormalizedMaintainabilityIndexFileResult result : results) {
			NormalizedMaintainabilityIndexResult mi = result
					.getNormalizedMaintainabilityIndexResult();
			Map<String, MetricValue<?>> row = new HashMap<>();
			row.put(NORM_MI_WOC.getName(),
					new MetricValue<Double>(mi.getNMIwoc(), NORM_MI_WOC));
			row.put(NORM_MI_CW.getName(), new MetricValue<Double>(
					mi.getNMIcw(), NORM_MI_CW));
			row.put(NORM_MI.getName(), new MetricValue<Double>(mi.getNMI(),
					NORM_MI));
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
					NormalizedMaintainabilityIndexEvaluatorParameter.ALL, row));
		}
		return values;
	}
}
