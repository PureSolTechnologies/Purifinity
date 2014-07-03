package com.puresoltechnologies.purifinity.server.core.api.evaluation.store;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;

@Remote
public interface EvaluatorStoreServiceRemote extends EvaluatorStore {

    String NAME = "java:global/server.app/server.core.impl/EvaluatorStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.evaluation.store.EvaluatorStoreServiceRemote";

}
