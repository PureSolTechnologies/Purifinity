package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.versioning.Version;

public class FileMetricsImpl extends AbstractMetrics implements FileMetrics {

    private static final long serialVersionUID = -3838440751773878139L;
    private final List<CodeRangeMetrics> codeRangeMetrics = new ArrayList<>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public FileMetricsImpl(String evaluatorId, Version evaluatorVersion, HashId hashId,
	    SourceCodeLocation sourceCodeLocation, Date time) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.sourceCodeLocation = sourceCodeLocation;
    }

    @JsonCreator
    public FileMetricsImpl(@JsonProperty("evaluatorId") String evaluatorId,
	    @JsonProperty("evaluatorVersion") Version evaluatorVersion, @JsonProperty("hashId") HashId hashId,
	    @JsonProperty("sourceCodeLocation") SourceCodeLocation sourceCodeLocation, @JsonProperty("time") Date time,
	    @JsonProperty("codeRangeMetrics") List<CodeRangeMetrics> codeRangeMetrics) {
	this(evaluatorId, evaluatorVersion, hashId, sourceCodeLocation, time);
	this.codeRangeMetrics.addAll(codeRangeMetrics);
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public void addCodeRangeMetrics(CodeRangeMetrics metrics) {
	codeRangeMetrics.add(metrics);
    }

    @Override
    @JsonIgnore
    public MetricParameter<?>[] getParameters() {
	Set<MetricParameter<?>> parameters = new HashSet<>();
	for (CodeRangeMetrics codeRangeMetric : codeRangeMetrics) {
	    parameters.addAll(Arrays.asList(codeRangeMetric.getParameters()));
	}
	return parameters.toArray(new MetricParameter[parameters.size()]);
    }

    @Override
    public List<CodeRangeMetrics> getCodeRangeMetrics() {
	return codeRangeMetrics;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((codeRangeMetrics == null) ? 0 : codeRangeMetrics.hashCode());
	result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
	result = prime * result + ((sourceCodeLocation == null) ? 0 : sourceCodeLocation.hashCode());
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
	FileMetricsImpl other = (FileMetricsImpl) obj;
	if (codeRangeMetrics == null) {
	    if (other.codeRangeMetrics != null)
		return false;
	} else if (!codeRangeMetrics.equals(other.codeRangeMetrics))
	    return false;
	if (hashId == null) {
	    if (other.hashId != null)
		return false;
	} else if (!hashId.equals(other.hashId))
	    return false;
	if (sourceCodeLocation == null) {
	    if (other.sourceCodeLocation != null)
		return false;
	} else if (!sourceCodeLocation.equals(other.sourceCodeLocation))
	    return false;
	return true;
    }

}
