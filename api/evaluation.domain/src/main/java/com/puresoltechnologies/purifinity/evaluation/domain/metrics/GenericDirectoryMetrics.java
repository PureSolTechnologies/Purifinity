package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.versioning.Version;

public class GenericDirectoryMetrics extends AbstractMetrics implements DirectoryMetrics {

    private static final long serialVersionUID = -7071488619641043222L;

    private final MetricParameter<?>[] parameters;
    private final Map<String, MetricValue<?>> values = new HashMap<>();

    private final HashId hashId;

    @JsonCreator
    public GenericDirectoryMetrics(@JsonProperty("evaluatorId") String evaluatorId,
	    @JsonProperty("evaluatorVersion") Version evaluatorVersion, @JsonProperty("hashId") HashId hashId,
	    @JsonProperty("time") Date time, @JsonProperty("parameters") MetricParameter<?>[] parameters,
	    @JsonProperty("values") Map<String, MetricValue<?>> values) {
	super(evaluatorId, evaluatorVersion, time);
	this.hashId = hashId;
	this.parameters = parameters;
	this.values.putAll(values);
    }

    @Override
    public HashId getHashId() {
	return hashId;
    }

    @Override
    public MetricParameter<?>[] getParameters() {
	return parameters;
    }

    public <T extends Number & Comparable<T> & Serializable> MetricValue<T> getValue(Parameter<T> parameter) {
	@SuppressWarnings("unchecked")
	MetricValue<T> t = (MetricValue<T>) values.get(parameter.getName());
	return t;
    }

    @Override
    public Map<String, MetricValue<?>> getValues() {
	return values;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
	result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
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
	GenericDirectoryMetrics other = (GenericDirectoryMetrics) obj;
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
	if (values == null) {
	    if (other.values != null)
		return false;
	} else if (!values.equals(other.values))
	    return false;
	return true;
    }

}
