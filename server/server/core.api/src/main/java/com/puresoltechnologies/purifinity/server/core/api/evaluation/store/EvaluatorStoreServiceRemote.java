package com.puresoltechnologies.purifinity.server.core.api.evaluation.store;

import javax.ejb.Remote;

@Remote
public interface EvaluatorStoreServiceRemote extends EvaluatorStore {

	String JNDI_NAME = "java:global/server.app/server.core.impl/EvaluatorStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreServiceRemote";

}
