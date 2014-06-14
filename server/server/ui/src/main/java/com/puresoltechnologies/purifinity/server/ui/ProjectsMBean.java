package com.puresoltechnologies.purifinity.server.ui;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProjectInformation;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;

@ApplicationScoped
@ManagedBean
public class ProjectsMBean {

	@Inject
	private AnalysisStore analysisStore;

	public List<AnalysisProject> getProjects() throws AnalysisStoreException {
		List<AnalysisProject> projects = new ArrayList<>();
		List<AnalysisProjectInformation> projectInformationList = analysisStore
				.readAllAnalysisProjectInformation();
		for (AnalysisProjectInformation projectInformation : projectInformationList) {
			projects.add(analysisStore.readAnalysisProject(projectInformation
					.getUUID()));
		}
		return projects;
	}

}
