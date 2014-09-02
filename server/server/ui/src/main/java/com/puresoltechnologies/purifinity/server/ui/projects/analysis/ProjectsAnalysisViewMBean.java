package com.puresoltechnologies.purifinity.server.ui.projects.analysis;

import java.io.Serializable;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.jms.JMSException;

import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;

@ViewScoped
@ManagedBean
public class ProjectsAnalysisViewMBean implements Serializable {

	private static final long serialVersionUID = 4856285592992961671L;

	@Inject
	private AnalysisService analysisService;

	public void triggerNewAnalysis(String uuid) {
		try {
			UUID projectUUID = UUID.fromString(uuid);
			analysisService.triggerNewAnalysis(projectUUID);
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}
