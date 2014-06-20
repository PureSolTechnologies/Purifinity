package com.puresoltechnologies.purifinity.server.ui.projects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

@ViewScoped
@ManagedBean
public class ProjectsViewMBean {

    @Inject
    private AnalysisStore analysisStore;

    private UUID projectUUID;
    private AnalysisProject project;
    private List<AnalysisProject> projects = new ArrayList<>();
    private List<AnalysisRunInformation> runs;

    @PostConstruct
    public void construct() {
	try {
	    ExternalContext externalContext = FacesContext.getCurrentInstance()
		    .getExternalContext();
	    Map<String, String> requestParameterMap = externalContext
		    .getRequestParameterMap();
	    String projectUUIDString = requestParameterMap.get("project");
	    if (projectUUIDString == null) {
		throw new IllegalStateException(
			"URL has to have a project parameter containing the project UUID.");
	    }
	    projectUUID = UUID.fromString(projectUUIDString);
	    project = analysisStore.readAnalysisProject(projectUUID);

	    projects = analysisStore.readAllAnalysisProjects();
	    runs = analysisStore.readAllRunInformation(projectUUID);
	} catch (AnalysisStoreException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<AnalysisProject> getProjects() {
	return projects;
    }

    public List<AnalysisRunInformation> getRuns() {
	return runs;
    }

    public UUID getProjectUUID() {
	return project.getInformation().getUUID();
    }

    public void setProjectUUID(String uuid) {
	projectUUID = UUID.fromString(uuid);
    }

    public String getProjectName() {
	return project.getSettings().getName();
    }
}
