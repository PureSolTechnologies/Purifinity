package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.List;

import javax.ejb.Local;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface EvaluatorServiceManager extends
		ServiceManager<EvaluatorServiceInformation, Evaluator> {

	/**
	 * This method creates a {@link Evaluator} instance out of the evaluator id.
	 * This evaluator is actually a proxy via remoting to the plugin.
	 * 
	 * @param evaluatorId
	 *            is the id of the evaluator.
	 * @return An {@link Evaluator} proxy is returned.
	 */
	public Evaluator createInstanceById(String evaluatorId);

	/**
	 * This method returns the evaluators in order of their dependencies to each
	 * other.
	 * 
	 * @return A {@link List} of {@link EvaluatorServiceInformation} is
	 *         returned.
	 */
	public List<EvaluatorServiceInformation> getServicesSortedByDependency();

	/**
	 * This method returns the evaluator information for the evaluator with the
	 * given id.
	 * 
	 * @param evaluatorId
	 *            is the id of the evaluator.
	 * @return An {@link EvaluatorServiceInformation} object is returned.
	 */
	public EvaluatorServiceInformation getEvaluatorPluginInformation(
			String evaluatorId);

	public boolean isActive(String evaluatorId);

	public void setActive(String evaluatorId, boolean active);
}
