package com.puresol.coding.client.charting.rendering;

import java.util.HashMap;
import java.util.Map;

import com.puresol.coding.client.charting.BorderPosition;

public class BorderPositionCounter {

    private final Map<BorderPosition, Integer> counter = new HashMap<BorderPosition, Integer>();

    public BorderPositionCounter() {
	super();
	clear();
    }

    public final void clear() {
	counter.clear();
	for (BorderPosition position : BorderPosition.values()) {
	    counter.put(position, 0);
	}
    }

    public final void add(BorderPosition position) {
	counter.put(position, counter.get(position) + 1);
    }

    public int get(BorderPosition position) {
	return counter.get(position);
    }

    public boolean hasPosition(BorderPosition position) {
	return counter.get(position) > 0;
    }
}
