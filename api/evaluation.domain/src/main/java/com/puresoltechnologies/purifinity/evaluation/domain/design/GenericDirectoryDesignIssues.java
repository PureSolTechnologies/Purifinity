package com.puresoltechnologies.purifinity.evaluation.domain.design;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

public class GenericDirectoryDesignIssues extends AbstractDesignIssues implements DirectoryDesignIssues {

    private final DesignIssueParameter[] parameters;
    private final Map<String, MetricValue<?>> designIssues = new HashMap<>();

    public GenericDirectoryDesignIssues(String evaluatorId, Version evaluatorVersion, HashId hashId, Date time,
	    DesignIssueParameter[] parameters, Map<String, MetricValue<?>> designIssues) {
	super(evaluatorId, evaluatorVersion, time);
	this.parameters = parameters;
	this.designIssues.putAll(designIssues);
    }

    @Override
    public DesignIssueParameter[] getParameters() {
	return parameters;
    }

    @Override
    public Map<String, MetricValue<?>> getDesignIssues() {
	// TODO Auto-generated method stub
	return null;
    }

}
