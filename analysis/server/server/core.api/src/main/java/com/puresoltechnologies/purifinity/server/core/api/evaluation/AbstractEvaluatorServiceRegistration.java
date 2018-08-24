package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

public abstract class AbstractEvaluatorServiceRegistration
	extends AbstractServiceRegistration<EvaluatorServiceInformation>implements EvaluatorRemoteService {

    @Override
    protected void registerInDatabase() {
    }

    @Override
    protected void unregisterInDatabase() {
    }

}
