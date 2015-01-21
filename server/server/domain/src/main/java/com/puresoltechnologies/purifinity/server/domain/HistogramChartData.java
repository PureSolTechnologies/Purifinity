package com.puresoltechnologies.purifinity.server.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.hash.HashId;

public class HistogramChartData implements Serializable {

	private static final long serialVersionUID = 6282396037915983592L;

	private final Map<HashId, List<Value<?>>> values;

	public HistogramChartData(Map<HashId, List<Value<?>>> values) {
		this.values = values;
	}

	public HistogramChartData() {
		values = new HashMap<HashId, List<Value<?>>>();
	}

	public Map<HashId, List<Value<?>>> getValues() {
		return values;
	}

	public List<Value<?>> getValues(HashId hashId) {
		return values.get(hashId);
	}
}
