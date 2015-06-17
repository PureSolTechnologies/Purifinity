package com.puresoltechnologies.purifinity.server.core.impl.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.batch.operations.JobOperator;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.StepExecution;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.common.job.StepInformation;
import com.puresoltechnologies.purifinity.server.common.job.StepProgress;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.jobs.JobState;
import com.puresoltechnologies.purifinity.server.core.api.analysis.jobs.JobStepState;
import com.puresoltechnologies.purifinity.server.core.api.analysis.jobs.PurifinityJobStates;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

@Stateless
public class AnalysisServiceBean implements AnalysisService {

	@Inject
	private Logger logger;

	@Inject
	private EventLoggerRemote eventLogger;

	@Inject
	private JobOperator jobOperator;

	@Inject
	private AnalysisStore analysisStore;

	@Inject
	private AnalyzerServiceManager analyzerRegistration;

	@PostConstruct
	public void initialize() {
		eventLogger.logEvent(AnalysisServiceEvents.createStartupEvent());
	}

	@PreDestroy
	public void shutdown() {
		eventLogger.logEvent(AnalysisServiceEvents.createShutdownEvent());
	}

	@Override
	public void triggerRunJob(String projectId) throws AnalysisStoreException {
		AnalysisProjectSettings analysisProjectSettings = analysisStore
				.readAnalysisProjectSettings(projectId);
		Properties jobParameters = new Properties();
		jobParameters.setProperty("project_id", projectId);
		jobParameters.setProperty("project_name",
				analysisProjectSettings.getName());
		jobOperator.start("ProjectAnalysis", jobParameters);
	}

	@Override
	public void abortRun(long jobId) {
		jobOperator.stop(jobId);
	}

	/**
	 * @return
	 */
	@Override
	public PurifinityJobStates getJobStates() {
		PurifinityJobStates states = new PurifinityJobStates(new Date());
		try {
			List<Long> runningExecutions = jobOperator
					.getRunningExecutions("ProjectAnalysis");
			for (long jobId : runningExecutions) {
				JobExecution jobExecution = jobOperator.getJobExecution(jobId);
				String projectId = jobExecution.getJobParameters().getProperty(
						"project_id");
				String projectName = jobExecution.getJobParameters()
						.getProperty("project_name");
				List<StepExecution> stepExecutions = jobOperator
						.getStepExecutions(jobId);
				List<JobStepState> stepStates = new ArrayList<JobStepState>();
				for (StepExecution stepExecution : stepExecutions) {
					if (stepExecution.getBatchStatus() == BatchStatus.STARTED) {
						String stepName = stepExecution.getStepName();
						long current = 0;
						long max = 1;
						Object persistentUserData = stepExecution
								.getPersistentUserData();
						if (persistentUserData != null) {
							if (StepInformation.class
									.isAssignableFrom(persistentUserData
											.getClass())) {
								StepInformation stepInformation = (StepInformation) persistentUserData;
								stepName = stepInformation.getName();
							}
							if (StepProgress.class
									.isAssignableFrom(persistentUserData
											.getClass())) {
								StepProgress stepInformation = (StepProgress) persistentUserData;
								current = stepInformation.getCurrentItem();
								max = stepInformation.getTotalItems();
							}
						}
						stepStates.add(new JobStepState(stepName, stepExecution
								.getBatchStatus().name(), current, max));
					}
				}
				if (stepStates.size() > 0) {
					states.addJobState(new JobState(jobId, projectId,
							projectName, stepStates));
				}
			}
		} catch (NoSuchJobException e) {
			logger.debug("Job 'ProjectAnalysis' does not exist. Maybe, it is not loaded, yet.");
		}
		return states;
	}

	@Override
	public Collection<AnalyzerServiceInformation> getAnalyzers() {
		return analyzerRegistration.getServices();
	}

	@Override
	public AnalyzerServiceInformation getAnalyzer(String analyzerId) {
		for (AnalyzerServiceInformation information : analyzerRegistration
				.getServices()) {
			if (information.getId().equals(analyzerId)) {
				return information;
			}
		}
		return null;
	}

	@Override
	public List<ConfigurationParameter<?>> getConfiguration(String analyzerId) {
		return analyzerRegistration.getInstanceById(analyzerId)
				.getConfigurationParameters();
	}

	@Override
	public boolean isEnabled(String analyzerId) {
		return analyzerRegistration.isActive(analyzerId);
	}

	@Override
	public void setActive(String analyzerId, boolean active) {
		analyzerRegistration.setActive(analyzerId, active);
	}
}
