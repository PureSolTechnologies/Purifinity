package com.puresoltechnologies.purifinity.server.ui.projects;

import java.text.SimpleDateFormat;
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
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

@ViewScoped
@ManagedBean
public class ProjectSummaryMBean {

    @Inject
    private AnalysisStore analysisStore;

    private UUID projectUUID;
    private List<AnalysisRunInformation> runs;
    private AnalysisProject project;
    private AnalysisRun lastRun;

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

	    AnalysisRunInformation lastRunInformation = analysisStore
		    .readLastAnalysisRun(projectUUID);
	    if (lastRunInformation != null) {
		lastRun = analysisStore.readAnalysisRun(lastRunInformation);
	    }
	    runs = analysisStore.readAllRunInformation(projectUUID);
	} catch (AnalysisStoreException e) {
	    throw new RuntimeException(e);
	}
    }

    public List<AnalysisRunInformation> getRuns() {
	return runs;
    }

    public String getName() {
	return project.getSettings().getName();
    }

    public String getDescription() {
	return project.getSettings().getDescription();
    }

    public String getLastRunDate() {
	if (lastRun == null) {
	    return "";
	}
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	return format.format(lastRun.getInformation().getStartTime());
    }
}
