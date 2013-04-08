package com.puresol.coding.evaluation.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class GenericMetricResults implements MetricResults {

	private static final long serialVersionUID = -8800107882009933892L;

	private final Set<Parameter<?>> parameters = new HashSet<Parameter<?>>();
	private final List<Map<String, Value<?>>> values = new ArrayList<Map<String, Value<?>>>();

	public void addParameter(Parameter<?> parameter) {
		parameters.add(parameter);
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return parameters;
	}

	public void addValue(Map<String, Value<?>> value) {
		values.add(value);
	}

	@Override
	public List<Map<String, Value<?>>> getValues() {
		return values;
	}

}
