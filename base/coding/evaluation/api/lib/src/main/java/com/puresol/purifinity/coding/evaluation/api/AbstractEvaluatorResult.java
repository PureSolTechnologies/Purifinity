package com.puresol.purifinity.coding.evaluation.api;

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

	@Override
	public final QualityLevel getQualityLevel() {
		return qualityLevel;
	}

	protected final void setQualityLevel(QualityLevel qualityLevel) {
		this.qualityLevel = qualityLevel;
	}
}
