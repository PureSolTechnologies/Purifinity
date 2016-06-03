package com.puresoltechnologies.purifinity.evaluation.domain.issues;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricValue;
import com.puresoltechnologies.versioning.Version;

/**
 * This class is the implementation for {@link DirectoryIssues}.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public class DirectoryIssuesImpl extends AbstractIssues implements DirectoryIssues {

    private static final long serialVersionUID = -1006974419305345122L;

    private final Map<String, MetricValue<?>> designIssues = new HashMap<>();

    public DirectoryIssuesImpl(String evaluatorId, Version evaluatorVersion, HashId hashId, Date time,
	    IssueParameter[] parameters, Map<String, MetricValue<?>> designIssues) {
	super(evaluatorId, evaluatorVersion, time, parameters);
	this.designIssues.putAll(designIssues);
    }

    @Override
    public Map<String, MetricValue<?>> getDesignIssues() {
	// TODO Auto-generated method stub
	return null;
    }

}
