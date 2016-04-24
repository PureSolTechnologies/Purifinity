package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.versioning.Version;

public class GenericProjectMetrics extends AbstractMetrics implements ProjectMetrics {

    private static final long serialVersionUID = 2692614300538471139L;

    private final MetricParameter<?>[] parameters;
    private final Map<String, MetricValue<?>> values = new HashMap<>();

    private final String projectId;
    private final long runId;

    public GenericProjectMetrics(String evaluatorId, Version evaluatorVersion, String projectId, long runId, Date time,
	    MetricParameter<?>[] parameters, Map<String, MetricValue<?>> values) {
	super(evaluatorId, evaluatorVersion, time);
	this.projectId = projectId;
	this.runId = runId;
	this.parameters = parameters;
	this.values.putAll(values);
    }

    @Override
    public String getProjectId() {
	return projectId;
    }

    @Override
    public long getRunId() {
	return runId;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return parameters;
    }

    @Override
    public Map<String, MetricValue<?>> getValues() {
	return values;
    }

}
