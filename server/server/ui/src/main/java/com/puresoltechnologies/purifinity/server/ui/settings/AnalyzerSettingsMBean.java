package com.puresoltechnologies.purifinity.server.ui.settings;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

@ApplicationScoped
@ManagedBean
public class AnalyzerSettingsMBean {

	@Inject
	private AnalyzerServiceManager analyzerServiceManager;

	public Collection<AnalyzerServiceInformation> getAnalyzers() {
		return analyzerServiceManager.getServices();
	}

	public Collection<PluginInformation> getPlugins() {
		return analyzerServiceManager.getPlugins();
	}

	public boolean isActivated(String analyzerId) {
		return analyzerServiceManager.isActive(analyzerId);
	}

	public void activate(String analyzerId) {
		analyzerServiceManager.setActive(analyzerId, true);
	}

	public void deactivate(String analyzerId) {
		analyzerServiceManager.setActive(analyzerId, false);
	}
}
