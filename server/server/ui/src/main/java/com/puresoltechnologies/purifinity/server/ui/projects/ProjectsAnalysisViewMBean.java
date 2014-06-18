package com.puresoltechnologies.purifinity.server.ui.projects;

import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.jms.JMSException;

import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;

@ViewScoped
@ManagedBean
public class ProjectsAnalysisViewMBean {

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
