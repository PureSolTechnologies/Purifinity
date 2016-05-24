package com.puresoltechnologies.purifinity.evaluation.domain.design;

import java.util.Map;

import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;

public interface DirectoryDesignIssues extends DesignIssues {

    public DesignIssueParameter[] getParameters();

    public Map<String, MetricValue<?>> getDesignIssues();
}
