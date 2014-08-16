package com.puresoltechnologies.purifinity.evaluation.domain;

import java.util.Date;

public abstract class AbstractEvaluatorResults implements EvaluationResults {

	private static final long serialVersionUID = 2746877960935350933L;

	private final String evaluatorId;
	private final Date time;

	public AbstractEvaluatorResults(String evaluatorId, Date time) {
		super();
		this.evaluatorId = evaluatorId;
		this.time = time;
	}

	@Override
	public final String getEvaluatorId() {
		return evaluatorId;
	}

	@Override
	public final Date getTime() {
		return time;
	}

}
