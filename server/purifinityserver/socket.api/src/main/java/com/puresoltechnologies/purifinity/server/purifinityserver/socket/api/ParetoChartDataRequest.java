package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.Serializable;
import java.util.UUID;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class ParetoChartDataRequest implements Serializable {

	private static final long serialVersionUID = -70003089293047336L;

	private final UUID analysisProject;
	private final UUID analysisRun;
	private final String evaluatorName;
	private final Parameter<?> parameter;
	private final CodeRangeType codeRangeType;

	public ParetoChartDataRequest(UUID analysisProject, UUID analysisRun,
			String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) {
		super();
		this.analysisProject = analysisProject;
		this.analysisRun = analysisRun;
		this.evaluatorName = evaluatorName;
		this.parameter = parameter;
		this.codeRangeType = codeRangeType;
	}

	public UUID getAnalysisProject() {
		return analysisProject;
	}

	public UUID getAnalysisRun() {
		return analysisRun;
	}

	public String getEvaluatorName() {
		return evaluatorName;
	}

	public Parameter<?> getParameter() {
		return parameter;
	}

	public CodeRangeType getCodeRangeType() {
		return codeRangeType;
	}

}
