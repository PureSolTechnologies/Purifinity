package com.puresoltechnologies.purifinity.server.ui.settings;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;

@ApplicationScoped
@ManagedBean
public class ProjectsTableMBean {

	@Inject
	private Logger logger;

	@Inject
	private AnalysisStore analysisStore;

	public List<AnalysisProject> getProjects() {
		try {
			List<AnalysisProject> projects = new ArrayList<>();
			for (AnalysisProjectInformation analysisProjectInformation : analysisStore
					.readAllAnalysisProjectInformation()) {
				AnalysisProject project = analysisStore
						.readAnalysisProject(analysisProjectInformation
								.getUUID());
				projects.add(project);
			}
			return projects;
		} catch (AnalysisStoreException e) {
			logger.error("Could not read project list.", e);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Could not read analysis projects.", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return new ArrayList<>();
		}
	}

}
