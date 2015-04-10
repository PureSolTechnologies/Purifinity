package com.puresoltechnologies.purifinity.server.socket.api;

import java.io.Serializable;
import java.util.UUID;

import com.puresoltechnologies.commons.domain.Parameter;

public class MetricsMapDataRequest implements Serializable {

	private static final long serialVersionUID = -70003089293047336L;

	private final UUID analysisProject;
	private final UUID analysisRun;
	private final String mapEvaluatorName;
	private final Parameter<?> mapParameter;
	private final String colorEvaluatorName;
	private final Parameter<?> colorParameter;

	public MetricsMapDataRequest(UUID analysisProject, UUID analysisRun,
			String mapEvaluatorName, Parameter<?> mapParameter,
			String colorEvaluatorName, Parameter<?> colorParameter) {
		super();
		this.analysisProject = analysisProject;
		this.analysisRun = analysisRun;
		this.mapEvaluatorName = mapEvaluatorName;
		this.mapParameter = mapParameter;
		this.colorEvaluatorName = colorEvaluatorName;
		this.colorParameter = colorParameter;
	}

	public UUID getAnalysisProject() {
		return analysisProject;
	}

	public UUID getAnalysisRun() {
		return analysisRun;
	}

	public String getMapEvaluatorName() {
		return mapEvaluatorName;
	}

	public Parameter<?> getMapParameter() {
		return mapParameter;
	}

	public String getColorEvaluatorName() {
		return colorEvaluatorName;
	}

	public Parameter<?> getColorParameter() {
		return colorParameter;
	}

}
