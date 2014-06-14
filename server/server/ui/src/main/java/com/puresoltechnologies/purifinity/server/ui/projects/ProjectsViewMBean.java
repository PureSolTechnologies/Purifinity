package com.puresoltechnologies.purifinity.server.ui.projects;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

@ViewScoped
@ManagedBean
public class ProjectsViewMBean {

	@Inject
	private AnalysisStore analysisStore;

	private AnalysisProject project;

	@PostConstruct
	public void construct() throws AnalysisStoreException {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		Map<String, String> requestParameterMap = externalContext
				.getRequestParameterMap();
		String projectUUID = requestParameterMap.get("project");
		project = analysisStore.readAnalysisProject(UUID
				.fromString(projectUUID));
	}

	public String getProjectName() {
		return project.getSettings().getName();
	}
}
