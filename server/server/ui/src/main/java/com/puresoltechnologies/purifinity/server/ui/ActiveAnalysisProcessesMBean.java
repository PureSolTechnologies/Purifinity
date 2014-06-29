package com.puresoltechnologies.purifinity.server.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStore;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStatusInformation;

@ManagedBean
@ViewScoped
public class ActiveAnalysisProcessesMBean implements Serializable {

    private static final long serialVersionUID = -279542548876135001L;

    @Inject
    private AnalysisProcessStateTracker analysisProcessStateTracker;

    @Inject
    private AnalysisStore analysisStore;

    public List<ActiveAnalysisProcess> getProcesses() {
	try {
	    List<ActiveAnalysisProcess> processes = new ArrayList<>();
	    List<AnalysisProcessStatusInformation> states = analysisProcessStateTracker
		    .readProcessStates();
	    for (AnalysisProcessStatusInformation state : states) {
		AnalysisProject analysisProject = analysisStore
			.readAnalysisProject(state.getProjectUUID());
		ActiveAnalysisProcess process = new ActiveAnalysisProcess(
			state.getProjectUUID(), state.getRunUUID(),
			state.getStarted(), analysisProject.getSettings()
				.getName(), state.getState(),
			state.getLastProgress());
		processes.add(process);
	    }
	    return processes;
	} catch (AnalysisStoreException e) {
	    throw new RuntimeException("Could not read project information.", e);
	}
    }

    public void stop(UUID projectUUID) {
	analysisProcessStateTracker.stopProcess(projectUUID);
    }
}
