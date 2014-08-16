package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class GenericMetrics {

    private final SourceCodeLocation sourceCodeLocation;
    private final CodeRangeType codeRangeType;
    private final String codeRangeName;
    private final Set<Parameter<?>> parameters = new HashSet<>();
    private final Map<String, Value<?>> values = new HashMap<>();

    public GenericMetrics(SourceCodeLocation sourceCodeLocation,
	    CodeRangeType codeRangeType, String codeRangeName,
	    Set<Parameter<?>> parameters, Map<String, Value<?>> values) {
	super();
	this.sourceCodeLocation = sourceCodeLocation;
	this.codeRangeType = codeRangeType;
	this.codeRangeName = codeRangeName;
	this.parameters.addAll(parameters);
	this.values.putAll(values);
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

    public Set<Parameter<?>> getParameters() {
	return parameters;
    }

    public Map<String, Value<?>> getValues() {
	return values;
    }

    public <T> Value<T> getValue(Parameter<T> parameter) {
	@SuppressWarnings("unchecked")
	Value<T> t = (Value<T>) values.get(parameter.getName());
	return t;
    }

}
