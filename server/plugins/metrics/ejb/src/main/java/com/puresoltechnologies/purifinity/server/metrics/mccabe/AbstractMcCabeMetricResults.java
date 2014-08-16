package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import static com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluatorParameter.QUALITY;
import static com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluatorParameter.QUALITY_LEVEL;
import static com.puresoltechnologies.purifinity.server.metrics.mccabe.McCabeMetricEvaluatorParameter.VG;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.AbstractMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public abstract class AbstractMcCabeMetricResults extends AbstractMetrics {

	private static final long serialVersionUID = 8270749745560040672L;

	public AbstractMcCabeMetricResults(String evaluatorId, Date time) {
		super(evaluatorId, time);
	}

	protected Map<String, MetricValue<?>> convertToRow(McCabeMetricResult result) {
		Map<String, MetricValue<?>> row = new HashMap<>();
		if (result != null) {
			row.put(VG.getName(),
					new MetricValue<Integer>(result.getCyclomaticComplexity(),
							VG));
			SourceCodeQuality quality = result.getQuality();
			row.put(QUALITY.getName(), new MetricValue<SourceCodeQuality>(
					quality, QUALITY));
			if (quality != SourceCodeQuality.UNSPECIFIED) {
				row.put(QUALITY_LEVEL.getName(), new MetricValue<QualityLevel>(
						new QualityLevel(quality), QUALITY_LEVEL));
			}
		}
		return row;
	}

}
