package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

/**
 * This is the interface for the remote evaluator registration.
 */
@Remote
public interface EvaluatorServiceManagerRemote extends
		ServiceManager<EvaluatorServiceInformation> {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/EvaluatorServiceManagerImpl!com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote";

}