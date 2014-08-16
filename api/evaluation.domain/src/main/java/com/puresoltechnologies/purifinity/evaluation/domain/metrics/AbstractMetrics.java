package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Date;

import com.puresoltechnologies.purifinity.evaluation.domain.AbstractEvaluatorResults;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public abstract class AbstractMetrics extends AbstractEvaluatorResults
		implements Metrics {

	private static final long serialVersionUID = -139741759862514010L;

	private QualityLevel qualityLevel = null;

	public AbstractMetrics(String evaluatorId, Date time) {
		super(evaluatorId, time);
	}

	@Override
	public final SourceCodeQuality getSourceQuality() {
		if (qualityLevel != null) {
			return qualityLevel.getQuality();
		} else {
			return SourceCodeQuality.UNSPECIFIED;
		}
	}

	public final void addQualityLevel(QualityLevel level) {
		if (qualityLevel == null) {
			qualityLevel = level;
		} else {
			qualityLevel.add(level);
		}
	}

	@Override
	public final QualityLevel getQualityLevel() {
		return qualityLevel;
	}

	protected final void setQualityLevel(QualityLevel qualityLevel) {
		this.qualityLevel = qualityLevel;
	}

}
