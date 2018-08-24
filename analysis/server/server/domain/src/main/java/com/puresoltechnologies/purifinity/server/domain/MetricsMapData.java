package com.puresoltechnologies.purifinity.server.domain;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.domain.Value;
import com.puresoltechnologies.commons.misc.hash.HashId;

/**
 * This value object contains the data for a Metric Map. A metric map just needs
 * the {@link HashId} of the file system node and the list of values which are
 * associated with it. The values are a list because a file system node may
 * contain multiple value like file for different code ranges (methods,
 * classes,...).
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MetricsMapData {

	private final Map<HashId, Map<String, Value<? extends Number>>> mapData = new HashMap<>();
	private final Map<HashId, Map<String, Value<?>>> colorData = new HashMap<>();

	public MetricsMapData(
			Map<HashId, Map<String, Value<? extends Number>>> mapValues,
			Map<HashId, Map<String, Value<?>>> colorValues) {
		mapData.putAll(mapValues);
		colorData.putAll(colorValues);
	}

	public MetricsMapData() {
	}

	public Map<String, Value<? extends Number>> getMapValues(HashId hashId) {
		return mapData.get(hashId);
	}

	public Map<String, Value<?>> getColorValues(HashId hashId) {
		return colorData.get(hashId);
	}

}
