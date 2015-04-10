package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.Collection;
import java.util.Set;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

/**
 * This is the interface for evaluation service. This service provides
 * information about the evaluators.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface EvaluationService {

	public Collection<EvaluatorServiceInformation> getEvaluators();

	public EvaluatorServiceInformation getEvaluator(String evaluatorId);

	public Set<ConfigurationParameter<?>> getConfiguration(String evaluatorId);

	public boolean isEnabled(String evaluatorId);

	public void setActive(String evaluatorId, boolean enabled);

}
