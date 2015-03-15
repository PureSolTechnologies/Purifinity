package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.versioning.Version;

public class GenericFileMetrics extends AbstractMetrics implements FileMetrics {

    private static final long serialVersionUID = -3838440751773878139L;

    private final Set<MetricParameter<?>> parameters = new LinkedHashSet<>();
    private final List<GenericCodeRangeMetrics> values = new ArrayList<>();

    private final HashId hashId;
    private final SourceCodeLocation sourceCodeLocation;

    public GenericFileMetrics(String evaluatorId, Version evaluatorVersion,
	    HashId hashId, SourceCodeLocation sourceCodeLocation, Date time,
	    Set<MetricParameter<?>> parameters) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.sourceCodeLocation = sourceCodeLocation;
	this.parameters.addAll(parameters);
    }

    @JsonCreator
    public GenericFileMetrics(
	    @JsonProperty("evaluatorId") String evaluatorId,
	    @JsonProperty("evaluatorId") Version evaluatorVersion,
	    @JsonProperty("hashId") HashId hashId,
	    @JsonProperty("sourceCodeLocation") SourceCodeLocation sourceCodeLocation,
	    @JsonProperty("time") Date time,
	    @JsonProperty("parameters") Set<MetricParameter<?>> parameters,
	    @JsonProperty("values") List<GenericCodeRangeMetrics> values) {
	this(evaluatorId, evaluatorVersion, hashId, sourceCodeLocation, time,
		parameters);
	this.parameters.addAll(parameters);
	this.values.addAll(values);
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public void addCodeRangeMetrics(GenericCodeRangeMetrics metrics) {
	values.add(metrics);
    }

    @Override
    public Set<MetricParameter<?>> getParameters() {
	return parameters;
    }

    @Override
    public List<GenericCodeRangeMetrics> getValues() {
	return values;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
	result = prime * result
		+ ((parameters == null) ? 0 : parameters.hashCode());
	result = prime
		* result
		+ ((sourceCodeLocation == null) ? 0 : sourceCodeLocation
			.hashCode());
	result = prime * result + ((values == null) ? 0 : values.hashCode());
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
	GenericFileMetrics other = (GenericFileMetrics) obj;
	if (hashId == null) {
	    if (other.hashId != null)
		return false;
	} else if (!hashId.equals(other.hashId))
	    return false;
	if (parameters == null) {
	    if (other.parameters != null)
		return false;
	} else if (!parameters.equals(other.parameters))
	    return false;
	if (sourceCodeLocation == null) {
	    if (other.sourceCodeLocation != null)
		return false;
	} else if (!sourceCodeLocation.equals(other.sourceCodeLocation))
	    return false;
	if (values == null) {
	    if (other.values != null)
		return false;
	} else if (!values.equals(other.values))
	    return false;
	return true;
    }

}
