package com.puresol.coding.metrics;

import com.puresol.coding.CodeRange;

/**
 * This is an interface for a single code range metric.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeRangeMetric extends Metric {

    /**
     * This method returns evaluated code range.
     * 
     * @return A code range object is returned.
     */
    public CodeRange getCodeRange();

}
