package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import java.util.List;

import javax.ejb.Local;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorPluginInformation;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface EvaluatorPluginService extends
	PluginService<EvaluatorPluginInformation> {

    public Evaluator createInstance(String jndi);

    public List<EvaluatorPluginInformation> getServicesSortedByDependency();

}
