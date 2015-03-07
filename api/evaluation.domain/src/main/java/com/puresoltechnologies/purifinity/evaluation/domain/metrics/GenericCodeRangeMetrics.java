package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class GenericCodeRangeMetrics implements Serializable {

    private static final long serialVersionUID = -598831823857817995L;
    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final Set<MetricParameter<?>> parameters = new HashSet<>();
    private final Map<String, MetricValue<?>> values = new HashMap<>();

    public GenericCodeRangeMetrics(SourceCodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    Set<MetricParameter<?>> parameters,
	    Map<String, MetricValue<?>> values) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.parameters.addAll(parameters);
	this.values.putAll(values);
    }

    public GenericCodeRangeMetrics(SourceCodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    Set<MetricParameter<?>> parameters,
	    Collection<MetricValue<?>> values) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.parameters.addAll(parameters);
	for (MetricValue<?> value : values) {
	    this.values.put(value.getParameter().getName(), value);
	}
    }

    public SourceCodeLocation getSourceCodeLocation() {
	return sourceCodeLocation;
    }

    public CodeRangeType getCodeRangeType() {
	return codeRangeType;
    }

    public String getCodeRangeName() {
	return codeRangeName;
    }

    public Set<MetricParameter<?>> getParameters() {
	return parameters;
    }

    public Map<String, MetricValue<?>> getValues() {
	return values;
    }

    public <T extends Number & Serializable & Comparable<T>> MetricValue<T> getValue(
	    MetricParameter<T> parameter) {
	@SuppressWarnings("unchecked")
	MetricValue<T> t = (MetricValue<T>) values.get(parameter.getName());
	return t;
    }
}
