package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GenericProjectMetrics extends AbstractMetrics implements
	ProjectMetrics {

    private static final long serialVersionUID = 2692614300538471139L;

    private final Set<MetricParameter<?>> parameters = new LinkedHashSet<>();
    private final Map<String, MetricValue<?>> values = new HashMap<>();

    private final UUID projectUUID;
    private final UUID runUUID;

    public GenericProjectMetrics(String evaluatorId, UUID projectUUID,
	    UUID runUUID, Date time, Set<MetricParameter<?>> parameters,
	    Map<String, MetricValue<?>> values) {
	super(evaluatorId, time);
	this.projectUUID = projectUUID;
	this.runUUID = runUUID;
	this.parameters.addAll(parameters);
	this.values.putAll(values);
    }

    @Override
    public UUID getProjectUUID() {
	return projectUUID;
    }

    @Override
    public UUID getRunUUID() {
	return runUUID;
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return parameters;
    }

    @Override
    public Map<String, MetricValue<?>> getValues() {
	return values;
    }

}
