package com.puresoltechnologies.purifinity.server.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStateTracker;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessStatusInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.states.AnalysisProcessTransition;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;

@ManagedBean
@ViewScoped
public class ActiveAnalysisProcessesMBean implements Serializable {

	private static final long serialVersionUID = -279542548876135001L;

	@Inject
	private AnalysisProcessStateTracker analysisProcessStateTracker;

	@Inject
	private AnalysisStore analysisStore;

	public List<ActiveAnalysisProcess> getProcesses() {
		List<ActiveAnalysisProcess> processes = new ArrayList<>();
		List<AnalysisProcessStatusInformation> states = analysisProcessStateTracker
				.readProcessStates();
		for (AnalysisProcessStatusInformation state : states) {
			AnalysisProject analysisProject;
			try {
				analysisProject = analysisStore.readAnalysisProject(state
						.getProjectUUID());
			} catch (AnalysisStoreException e) {
				/*
				 * A process was found for a project which is not present
				 * anymore. This process can be aborted.
				 */
				analysisProcessStateTracker.changeProcessState(
						state.getProjectUUID(), state.getRunUUID(),
						AnalysisProcessTransition.ABORT);
				continue;
			}
			ActiveAnalysisProcess process = new ActiveAnalysisProcess(
					state.getProjectUUID(), state.getRunUUID(),
					state.getStarted(),
					analysisProject.getSettings().getName(), state.getState(),
					state.getLastProgress());
			processes.add(process);
		}
		return processes;
	}

	public void stop(UUID projectUUID) {
		analysisProcessStateTracker.stopProcess(projectUUID);
	}
}
