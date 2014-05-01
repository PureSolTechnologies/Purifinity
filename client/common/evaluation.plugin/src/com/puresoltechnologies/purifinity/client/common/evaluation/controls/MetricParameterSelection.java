package com.puresoltechnologies.purifinity.client.common.evaluation.controls;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;

public class MetricParameterSelection {

	private final EvaluatorFactory evaluatorFactory;
	private final Parameter<?> parameter;
	private final CodeRangeType codeRangeType;

	public MetricParameterSelection(EvaluatorFactory evaluatorFactory,
			Parameter<?> parameter, CodeRangeType codeRangeType) {
		super();
		this.evaluatorFactory = evaluatorFactory;
		this.parameter = parameter;
		this.codeRangeType = codeRangeType;
	}

	public EvaluatorFactory getEvaluatorFactory() {
		return evaluatorFactory;
	}

	public Parameter<?> getParameter() {
		return parameter;
	}

	public CodeRangeType getCodeRangeType() {
		return codeRangeType;
	}

	public boolean isComplete() {
		return (evaluatorFactory != null) && (parameter != null)
				&& (codeRangeType != null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codeRangeType == null) ? 0 : codeRangeType.hashCode());
		result = prime
				* result
				+ ((evaluatorFactory == null) ? 0 : evaluatorFactory.hashCode());
		result = prime * result
				+ ((parameter == null) ? 0 : parameter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetricParameterSelection other = (MetricParameterSelection) obj;
		if (codeRangeType != other.codeRangeType)
			return false;
		if (evaluatorFactory == null) {
			if (other.evaluatorFactory != null)
				return false;
		} else if (!evaluatorFactory.equals(other.evaluatorFactory))
			return false;
		if (parameter == null) {
			if (other.parameter != null)
				return false;
		} else if (!parameter.equals(other.parameter))
			return false;
		return true;
	}

}
