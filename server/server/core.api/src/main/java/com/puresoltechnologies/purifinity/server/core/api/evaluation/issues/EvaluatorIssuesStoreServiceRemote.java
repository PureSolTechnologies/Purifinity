package com.puresoltechnologies.purifinity.server.core.api.evaluation.issues;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface EvaluatorIssuesStoreServiceRemote extends EvaluatorIssuesStore {

    String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/EvaluatorIssuesStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStoreServiceRemote";

}
