package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.misc.HashId;

public class GenericDirectoryMetrics extends AbstractMetrics implements
		DirectoryMetrics {

	private static final long serialVersionUID = -7071488619641043222L;

	private final Set<Parameter<?>> parameters = new LinkedHashSet<>();
	private final Map<String, MetricValue<?>> values = new HashMap<>();

	private final HashId hashId;

	public GenericDirectoryMetrics(String evaluatorId, HashId hashId,
			Date time, Set<Parameter<?>> parameters,
			Map<String, MetricValue<?>> values) {
		super(evaluatorId, time);
		this.hashId = hashId;
		this.parameters.addAll(parameters);
		this.values.putAll(values);
	}

	@Override
	public HashId getHashId() {
		return hashId;
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return parameters;
	}

	public <T extends Comparable<T> & Serializable> MetricValue<T> getValue(
			Parameter<T> parameter) {
		@SuppressWarnings("unchecked")
		MetricValue<T> t = (MetricValue<T>) values.get(parameter.getName());
		return t;
	}

	@Override
	public Map<String, MetricValue<?>> getValues() {
		return values;
	}

}
