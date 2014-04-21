package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.util.UUID;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class ChartData1DRequest {

	private final UUID analysisProject;
	private final UUID analysisRun;
	private final String evaluatorName;
	private final Parameter<?> parameter;
	private final CodeRangeType codeRangeType;

	public ChartData1DRequest(UUID analysisProject, UUID analysisRun,
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
