package com.puresoltechnologies.purifinity.server.ui.settings;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

@ApplicationScoped
@ManagedBean
public class EvaluatorSettingsMBean {

	@Inject
	private EvaluatorServiceManager evaluatorPluginService;

	public Collection<EvaluatorServiceInformation> getEvaluators() {
		return evaluatorPluginService.getServices();
	}

	public Collection<PluginInformation> getPlugins() {
		return evaluatorPluginService.getPlugins();
	}

	public boolean isActivated(String evaluatorId) {
		return evaluatorPluginService.isActive(evaluatorId);

	}

	public void activate(String evaluatorId) {
		evaluatorPluginService.setActive(evaluatorId, true);
	}

	public void deactivate(String evaluatorId) {
		evaluatorPluginService.setActive(evaluatorId, false);
	}

}
