package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

/**
 * This is the interface for the remote evaluator registration.
 */
@Remote
public interface EvaluatorServiceManagerRemote extends ServiceManager<EvaluatorServiceInformation, Evaluator> {

    public static final String JNDI_NAME = "java:global/server.app/com-puresoltechnologies-purifinity-server-server.core.impl-"
	    + BuildInformation.getVersion()
	    + "/EvaluatorServiceManagerImpl!com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote";

}
