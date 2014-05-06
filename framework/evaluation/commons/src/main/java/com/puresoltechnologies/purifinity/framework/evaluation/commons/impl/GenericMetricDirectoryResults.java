package com.puresoltechnologies.purifinity.framework.evaluation.commons.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.purifinity.evaluation.api.AbstractEvaluatorResult;
import com.puresoltechnologies.purifinity.evaluation.domain.MetricDirectoryResults;

public class GenericMetricDirectoryResults extends AbstractEvaluatorResult
		implements MetricDirectoryResults {

	private static final long serialVersionUID = -8800107882009933892L;

	private final Set<Parameter<?>> parameters = new HashSet<Parameter<?>>();
	private final Map<String, Value<?>> values = new HashMap<String, Value<?>>();

	public void addParameter(Parameter<?> parameter) {
		parameters.add(parameter);
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return parameters;
	}

	public void addValue(String name, Value<?> value) {
		values.put(name, value);
	}

	@Override
	public Map<String, Value<?>> getValues() {
		return values;
	}

}