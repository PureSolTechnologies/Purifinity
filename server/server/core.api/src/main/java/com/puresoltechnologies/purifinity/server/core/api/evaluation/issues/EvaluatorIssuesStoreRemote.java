package com.puresoltechnologies.purifinity.server.core.api.evaluation.issues;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface EvaluatorIssuesStoreRemote extends CommonEvaluatorIssuesStore {

    String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/EvaluatorIssuesStoreBean!com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStoreRemote";

}
