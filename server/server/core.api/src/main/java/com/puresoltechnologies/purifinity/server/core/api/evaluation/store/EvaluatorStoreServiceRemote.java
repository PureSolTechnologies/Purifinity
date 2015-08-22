package com.puresoltechnologies.purifinity.server.core.api.evaluation.store;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface EvaluatorStoreServiceRemote extends EvaluatorStore {

    String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/EvaluatorStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreServiceRemote";

}
