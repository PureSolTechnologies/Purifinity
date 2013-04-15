package com.puresol.coding.evaluation.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class GenericMetricDirectoryResults implements MetricDirectoryResults {

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
