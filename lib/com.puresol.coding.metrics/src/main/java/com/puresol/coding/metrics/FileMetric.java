package com.puresol.coding.metrics;

/**
 * This is an interface for a single file metric. The metric may be used by the
 * project or directory metric to calculate their results. As well, an
 * implementation of this interface may use the cached intermediate results by
 * the other metrics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface FileMetric extends Metric {
}
