package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

/**
 * This interface is used to transport issues for directories.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public interface DirectoryIssues extends Issues {

    public Map<String, MetricValue<?>> getDesignIssues();
}
