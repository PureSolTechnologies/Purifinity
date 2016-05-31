package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.versioning.Version;

public class RunMetricsImpl extends AbstractMetrics implements RunMetrics {

    private static final long serialVersionUID = -815011058948733680L;

    private final MetricParameter<?>[] parameters;
    private final Map<HashId, FileMetricsImpl> fileMetrics = new HashMap<>();
    private final Map<HashId, DirectoryMetricsImpl> directoryMetrics = new HashMap<>();

    public RunMetricsImpl(String evaluatorId, Version evaluatorVersion, Date time, MetricParameter<?>[] parameters) {
	super(evaluatorId, evaluatorVersion, time);
	this.parameters = parameters;
    }

    @JsonCreator
    public RunMetricsImpl(@JsonProperty("evaluatorId") String evaluatorId,
	    @JsonProperty("evaluatorVersion") Version evaluatorVersion, @JsonProperty("time") Date time,
	    @JsonProperty("parameters") MetricParameter<?>[] parameters,
	    @JsonProperty("fileMetrics") Map<HashId, FileMetricsImpl> fileMetrics,
	    @JsonProperty("directoryMetrics") Map<HashId, DirectoryMetricsImpl> directoryMetrics) {
	this(evaluatorId, evaluatorVersion, time, parameters);
	if (fileMetrics != null) {
	    this.fileMetrics.putAll(fileMetrics);
	}
	if (directoryMetrics != null) {
	    this.directoryMetrics.putAll(directoryMetrics);
	}
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return parameters;
    }

    public void add(FileMetricsImpl fileMetrics) {
	this.fileMetrics.put(fileMetrics.getHashId(), fileMetrics);
    }

    public void add(DirectoryMetricsImpl directoryMetrics) {
	this.directoryMetrics.put(directoryMetrics.getHashId(), directoryMetrics);
    }

    public Map<HashId, FileMetricsImpl> getFileMetrics() {
	return fileMetrics;
    }

    public Map<HashId, DirectoryMetricsImpl> getDirectoryMetrics() {
	return directoryMetrics;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((directoryMetrics == null) ? 0 : directoryMetrics.hashCode());
	result = prime * result + ((fileMetrics == null) ? 0 : fileMetrics.hashCode());
	result = prime * result + Arrays.hashCode(parameters);
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
	RunMetricsImpl other = (RunMetricsImpl) obj;
	if (directoryMetrics == null) {
	    if (other.directoryMetrics != null)
		return false;
	} else if (!directoryMetrics.equals(other.directoryMetrics))
	    return false;
	if (fileMetrics == null) {
	    if (other.fileMetrics != null)
		return false;
	} else if (!fileMetrics.equals(other.fileMetrics))
	    return false;
	if (!Arrays.equals(parameters, other.parameters))
	    return false;
	return true;
    }

}
