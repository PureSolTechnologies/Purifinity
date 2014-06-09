package com.puresoltechnologies.purifinity.server.ui.system;

import java.util.Collection;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginService;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryTypePluginService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

@ApplicationScoped
@ManagedBean
public class SystemPluginsMBean {

	@Inject
	private AnalyzerPluginService analyzerPluginService;

	@Inject
	private EvaluatorPluginService evaluatorPluginService;

	@Inject
	private RepositoryTypePluginService repositoryTypePluginService;

	public Collection<AnalyzerInformation> getAnalyzers() {
		return analyzerPluginService.getServices();
	}

	public Collection<EvaluatorInformation> getEvaluators() {
		return evaluatorPluginService.getServices();
	}

	public Collection<RepositoryType> getRepositoryTypes() {
		return repositoryTypePluginService.getServices();
	}

}
