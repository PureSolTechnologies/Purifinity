package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;

/**
 * This value object contains the data for a Histogram Chart. A histogram chart
 * just needs the {@link HashId} of the file system node and the list of values
 * which are associated with it. The values are a list because a file system
 * node may contain multiple value like file for different code ranges (methods,
 * classes,...).
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParetoChartData {

	private final Map<HashId, Map<String, Value<? extends Number>>> data = new HashMap<>();

	public ParetoChartData(
			Map<HashId, Map<String, Value<? extends Number>>> values) {
		data.putAll(values);
	}

	public ParetoChartData() {
	}

	public Map<String, Value<? extends Number>> getValues(HashId hashId) {
		return data.get(hashId);
	}

}