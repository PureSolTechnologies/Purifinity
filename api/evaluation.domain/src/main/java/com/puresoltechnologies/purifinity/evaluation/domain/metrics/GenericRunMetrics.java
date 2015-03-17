package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.versioning.Version;

public class GenericRunMetrics extends AbstractMetrics implements Metrics {

    private static final long serialVersionUID = -815011058948733680L;

    private final Set<MetricParameter<?>> parameters = new HashSet<>();
    private final Map<HashId, GenericFileMetrics> fileMetrics = new HashMap<>();
    private final Map<HashId, GenericDirectoryMetrics> directoriesMetrics = new HashMap<>();

    public GenericRunMetrics(String evaluatorId, Version evaluatorVersion,
	    Date time, Set<MetricParameter<?>> parameters) {
	super(evaluatorId, evaluatorVersion, time);
	if (parameters != null) {
	    this.parameters.addAll(parameters);
	}
    }

    @JsonCreator
    public GenericRunMetrics(
	    @JsonProperty("evaluatorId") String evaluatorId,
	    @JsonProperty("evaluatorVersion") Version evaluatorVersion,
	    @JsonProperty("time") Date time,
	    @JsonProperty("parameters") Set<MetricParameter<?>> parameters,
	    @JsonProperty("fileMetrics") Map<HashId, GenericFileMetrics> fileMetrics,
	    @JsonProperty("directoryMetrics") Map<HashId, GenericDirectoryMetrics> directoryMetrics) {
	this(evaluatorId, evaluatorVersion, time, parameters);
	if (fileMetrics != null) {
	    this.fileMetrics.putAll(fileMetrics);
	}
	if (directoryMetrics != null) {
	    this.directoriesMetrics.putAll(directoryMetrics);
	}
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return parameters;
    }

    public void add(GenericFileMetrics fileMetrics) {
	this.fileMetrics.put(fileMetrics.getHashId(), fileMetrics);
    }

    public void add(GenericDirectoryMetrics directoryMetrics) {
	this.directoriesMetrics.put(directoryMetrics.getHashId(),
		directoryMetrics);
    }

    public Map<HashId, GenericFileMetrics> getFileMetrics() {
	return fileMetrics;
    }

    public Map<HashId, GenericDirectoryMetrics> getDirectoryMetrics() {
	return directoriesMetrics;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime
		* result
		+ ((directoriesMetrics == null) ? 0 : directoriesMetrics
			.hashCode());
	result = prime * result
		+ ((fileMetrics == null) ? 0 : fileMetrics.hashCode());
	result = prime * result
		+ ((parameters == null) ? 0 : parameters.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	GenericRunMetrics other = (GenericRunMetrics) obj;
	if (directoriesMetrics == null) {
	    if (other.directoriesMetrics != null)
		return false;
	} else if (!directoriesMetrics.equals(other.directoriesMetrics))
	    return false;
	if (fileMetrics == null) {
	    if (other.fileMetrics != null)
		return false;
	} else if (!fileMetrics.equals(other.fileMetrics))
	    return false;
	if (parameters == null) {
	    if (other.parameters != null)
		return false;
	} else if (!parameters.equals(other.parameters))
	    return false;
	return true;
    }

}
