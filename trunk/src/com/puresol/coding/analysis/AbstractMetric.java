/***************************************************************************
 *
 *   AbstractMetric.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import com.puresol.coding.evaluator.metric.Metric;

abstract public class AbstractMetric implements Metric {

	private static final long serialVersionUID = 538037000519050562L;

	private final CodeRange range;

	public AbstractMetric(CodeRange range) {
		this.range = range;
	}

	public final CodeRange getCodeRange() {
		return range;
	}
}
