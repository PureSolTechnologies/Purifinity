package com.puresol.coding.metrics;

/**
 * This is an interface for a project metric. Project metrics are calculated on
 * a complete project.
 * 
 * For performance reasons classes implementing this interface might use the
 * directory and file metrics to get their information. When the information is
 * cached on disc or database, the speed might go up significantly.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProjectMetric extends Metric {
}
