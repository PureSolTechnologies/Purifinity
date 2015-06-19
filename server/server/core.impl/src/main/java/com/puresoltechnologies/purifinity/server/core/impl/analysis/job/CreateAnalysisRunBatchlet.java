package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.Date;
import java.util.Properties;

import javax.batch.api.Batchlet;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

/**
 * This message driven beans gets all analysis requests for triggering the
 * analyzes. This bean's responsibility is to avoid collisions for analyzes of
 * same projects. If a project is already under analysis, the current request is
 * requeued.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
@Named("CreateAnalysisRunBatchlet")
public class CreateAnalysisRunBatchlet implements Batchlet {

	@Inject
	private EventLoggerRemote eventLogger;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private JobOperator jobOperator;

	@Inject
	private JobContext jobContext;

	@Override
	public String process() throws Exception {
		try {
			Properties properties = jobContext.getProperties();
			String projectId = properties.getProperty("project_id");

			// if (jobOperator.getJobInstanceCount("ProjectAnalysis") > 2) {
			/*
			 * There is already a process running for the project, so we
			 * re-queue here.
			 */
			// TODO
			// messageSender.sendMessageWithDelay(projectAnalysisStartQueue,
			// projectId.toString(), 60000);
			/*
			 * We finish here and let the re-delivery do the job.
			 */
			// return "ALREADY QUEUED";
			// }

			AnalysisProject analysisProject = analysisStore
					.readAnalysisProject(projectId);
			eventLogger.logEvent(AnalysisEvents.createQueueAnalysisEvent(
					projectId, analysisProject.getSettings().getName()));

			// TODO the actual logic for the collision avoidance is still
			// missing.
			AnalysisRunInformation analysisRunInformation = analysisStore
					.createAnalysisRun(analysisProject.getInformation()
							.getProjectId(), new Date(), -1, "",
							analysisProject.getSettings()
									.getFileSearchConfiguration());

			jobContext.setTransientUserData(new AnalysisJobContext(new Date(),
					analysisProject, analysisRunInformation));

			return "SUCCESSFUL";
		} catch (AnalysisStoreException e) {
			// An issue occurred, re-queue the request.
			eventLogger.logEvent(AnalysisEvents.createGeneralError(e));
			throw new RuntimeException("Could not start the project analysis.",
					e);
		}
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
	}
}
