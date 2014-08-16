package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

public class GenericCodeRangeMetrics {

	private final SourceCodeLocation sourceCodeLocation;
	private final CodeRangeType codeRangeType;
	private final String codeRangeName;
	private final Set<Parameter<?>> parameters = new HashSet<>();
	private final Map<String, MetricValue<?>> values = new HashMap<>();

	public GenericCodeRangeMetrics(SourceCodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			Set<Parameter<?>> parameters, Map<String, MetricValue<?>> values) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		this.codeRangeType = codeRangeType;
		this.codeRangeName = codeRangeName;
		this.parameters.addAll(parameters);
		this.values.putAll(values);
	}

	public GenericCodeRangeMetrics(SourceCodeLocation sourceCodeLocation,
			CodeRangeType codeRangeType, String codeRangeName,
			Set<Parameter<?>> parameters, Collection<MetricValue<?>> values) {
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

	public Set<Parameter<?>> getParameters() {
		return parameters;
	}

	public Map<String, MetricValue<?>> getValues() {
		return values;
	}

	public <T> Value<T> getValue(Parameter<T> parameter) {
		@SuppressWarnings("unchecked")
		Value<T> t = (Value<T>) values.get(parameter.getName());
		return t;
	}

}
