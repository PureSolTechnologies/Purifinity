package com.puresoltechnologies.purifinity.server.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.domain.Value;
import com.puresoltechnologies.commons.misc.hash.HashId;

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
public class ParetoChartData implements Serializable {

	private static final long serialVersionUID = 3589012218679125578L;

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
