package com.puresoltechnologies.purifinity.server.purifinityserver.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;

public class ChartData1D implements Serializable {

	private static final long serialVersionUID = 6282396037915983592L;

	private final Map<HashId, List<Value<?>>> values;

	public ChartData1D(Map<HashId, List<Value<?>>> values) {
		this.values = values;
	}

	public ChartData1D() {
		values = new HashMap<HashId, List<Value<?>>>();
	}

	public Map<HashId, List<Value<?>>> getValues() {
		return values;
	}

	public List<Value<?>> getValues(HashId hashId) {
		return values.get(hashId);
	}
}
