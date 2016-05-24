package com.puresoltechnologies.purifinity.server.core.api.evaluation.design;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface EvaluatorDesignIssuesStoreServiceRemote extends EvaluatorDesignIssuesStore {

    String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/EvaluatorDesignIssuesStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.evaluation.design.EvaluatorDesignIssuesStoreServiceRemote";

}
