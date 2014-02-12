package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.HashMap;
import java.util.List;
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
public class HistogramChartData {

	private final Map<HashId, List<Value<?>>> data = new HashMap<>();

	public HistogramChartData(Map<HashId, List<Value<?>>> values) {
		data.putAll(values);
	}

	public HistogramChartData() {
	}

	public List<Value<?>> getValues(HashId hashId) {
		return data.get(hashId);
	}

}
