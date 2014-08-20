package com.puresoltechnologies.purifinity.server.ui.system;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypeServiceManager;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@ApplicationScoped
@ManagedBean
public class SystemPluginsMBean {

	@Inject
	private AnalyzerServiceManager analyzerPluginService;

	@Inject
	private EvaluatorServiceManager evaluatorPluginService;

	@Inject
	private RepositoryTypeServiceManager repositoryTypePluginService;

	public Collection<AnalyzerServiceInformation> getAnalyzers() {
		return analyzerPluginService.getServices();
	}

	public Collection<EvaluatorServiceInformation> getEvaluators() {
		return evaluatorPluginService.getServices();
	}

	public Collection<RepositoryType> getRepositoryTypes() {
		return repositoryTypePluginService.getServices();
	}

	public Collection<PluginInformation> getPlugins() {
		Set<PluginInformation> plugins = new LinkedHashSet<>();
		plugins.addAll(analyzerPluginService.getPlugins());
		plugins.addAll(evaluatorPluginService.getPlugins());
		plugins.addAll(repositoryTypePluginService.getPlugins());
		return plugins;
	}
}
