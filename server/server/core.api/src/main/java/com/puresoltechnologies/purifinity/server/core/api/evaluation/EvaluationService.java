package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.Collection;

import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

/**
 * This is the interface for evaluation service. This service provides
 * information about the evaluators.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface EvaluationService {

    public Collection<EvaluatorServiceInformation> getEvaluators();

}
