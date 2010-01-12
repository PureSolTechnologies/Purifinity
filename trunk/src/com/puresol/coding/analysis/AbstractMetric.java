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

import com.puresol.coding.CodeRange;

abstract public class AbstractMetric implements Analysis {

    private CodeRange range = null;

    public AbstractMetric(CodeRange range) {
	this.range = range;
    }

    public CodeRange getCodeRange() {
	return range;
    }
}
