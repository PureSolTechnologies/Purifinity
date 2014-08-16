package com.puresoltechnologies.purifinity.server.ui.projects;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;

@ViewScoped
@ManagedBean
public class ProjectsViewMBean implements Serializable {

	private static final long serialVersionUID = -856803428226667204L;

	@Inject
	private AnalysisStore analysisStore;

	private List<AnalysisProject> projects = new ArrayList<>();
	private List<AnalysisRunInformation> runs;
	private UUID projectUUID;
	private UUID runUUID;
	private AnalysisProject project;
	private AnalysisRun run;

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

			String runUUIDString = requestParameterMap.get("run");
			if (runUUIDString == null) {
				AnalysisRunInformation analysisRunInformation = analysisStore
						.readLastAnalysisRun(projectUUID);
				if (analysisRunInformation != null) {
					runUUID = analysisRunInformation.getRunUUID();
					run = analysisStore.readAnalysisRun(analysisRunInformation);
				}
			} else {
				runUUID = UUID.fromString(runUUIDString);
				AnalysisRunInformation analysisRunInformation = analysisStore
						.readAnalysisRunInformation(projectUUID, runUUID);
				run = analysisStore.readAnalysisRun(analysisRunInformation);
			}

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

	public String getProjectUUID() {
		return project.getInformation().getUUID().toString();
	}

	public void setProjectUUID(String uuid) {
		projectUUID = UUID.fromString(uuid);
	}

	public String getRunUUID() {
		return runUUID.toString();
	}

	public void setRunUUID(String runUUID) {
		this.runUUID = UUID.fromString(runUUID);
	}

	public String getProjectName() {
		return project.getSettings().getName();
	}

	public String getRunName() {
		if (run == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(run.getInformation().getStartTime());
	}
}
