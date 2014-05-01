package com.puresoltechnologies.purifinity.evaluation.api;

import com.puresoltechnologies.purifinity.evaluation.domain.EvaluationResult;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public abstract class AbstractEvaluatorResult implements EvaluationResult {

	private static final long serialVersionUID = -922469837562831456L;

	private QualityLevel qualityLevel = null;

	@Override
	public final SourceCodeQuality getSourceQuality() {
		if (qualityLevel != null) {
			return qualityLevel.getQuality();
		} else {
			return SourceCodeQuality.UNSPECIFIED;
		}
	}

	public final void addQualityLevel(QualityLevel level) {
		QualityLevel qualityLevel = getQualityLevel();
		if (qualityLevel == null) {
			setQualityLevel(level);
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
