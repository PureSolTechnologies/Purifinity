package com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface EvaluatorMetricsStoreServiceRemote extends EvaluatorMetricsStore {

    String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/EvaluatorMetricsStoreServiceBean!com.puresoltechnologies.purifinity.server.core.api.evaluation.metrics.EvaluatorMetricsStoreServiceRemote";

}
