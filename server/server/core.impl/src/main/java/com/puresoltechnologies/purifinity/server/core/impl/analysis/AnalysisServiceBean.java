package com.puresoltechnologies.purifinity.server.core.impl.analysis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.StepExecution;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.grammar.GrammarConverter;
import com.puresoltechnologies.parsers.grammar.GrammarException;
import com.puresoltechnologies.parsers.grammar.GrammarFile;
import com.puresoltechnologies.parsers.parser.ParseTreeNode;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.domain.LanguageGrammar;
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

@Stateless
public class AnalysisServiceBean implements AnalysisService {

    @Inject
    private Logger logger;

    @Inject
    private JobOperator jobOperator;

    @Inject
    private AnalysisStore analysisStore;

    @Inject
    private AnalyzerServiceManager analyzerRegistration;

    @Override
    public void triggerRunJob(String projectId) throws AnalysisStoreException {
	AnalysisProjectSettings analysisProjectSettings = analysisStore.readAnalysisProjectSettings(projectId);
	Properties jobParameters = new Properties();
	jobParameters.setProperty("project_id", projectId);
	jobParameters.setProperty("project_name", analysisProjectSettings.getName());
	jobOperator.start("ProjectAnalysis", jobParameters);
    }

    @Override
    public void abortRun(long jobId) {
	jobOperator.stop(jobId);
    }

    /**
     * @return A {@link PurifinityJobStates} object is returned containing all
     *         states of all running jobs.
     */
    @Override
    public PurifinityJobStates getJobStates() {
	PurifinityJobStates states = new PurifinityJobStates(new Date());
	try {
	    List<Long> runningExecutions = jobOperator.getRunningExecutions("ProjectAnalysis");
	    for (long jobId : runningExecutions) {
		JobExecution jobExecution = jobOperator.getJobExecution(jobId);
		String projectId = jobExecution.getJobParameters().getProperty("project_id");
		String projectName = jobExecution.getJobParameters().getProperty("project_name");
		List<StepExecution> stepExecutions = jobOperator.getStepExecutions(jobId);
		List<JobStepState> stepStates = new ArrayList<JobStepState>();
		for (StepExecution stepExecution : stepExecutions) {
		    if (stepExecution.getBatchStatus() == BatchStatus.STARTED) {
			String stepName = stepExecution.getStepName();
			long current = 0;
			long max = 1;
			Object persistentUserData = stepExecution.getPersistentUserData();
			if (persistentUserData != null) {
			    if (StepInformation.class.isAssignableFrom(persistentUserData.getClass())) {
				StepInformation stepInformation = (StepInformation) persistentUserData;
				stepName = stepInformation.getName();
			    }
			    if (StepProgress.class.isAssignableFrom(persistentUserData.getClass())) {
				StepProgress stepInformation = (StepProgress) persistentUserData;
				current = stepInformation.getCurrentItem();
				max = stepInformation.getTotalItems();
			    }
			}
			stepStates.add(new JobStepState(stepName, stepExecution.getBatchStatus().name(), current, max));
		    }
		}
		if (stepStates.size() > 0) {
		    states.addJobState(new JobState(jobId, projectId, projectName, stepStates));
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
	for (AnalyzerServiceInformation information : analyzerRegistration.getServices()) {
	    if (information.getId().equals(analyzerId)) {
		return information;
	    }
	}
	return null;
    }

    @Override
    public List<ConfigurationParameter<?>> getConfiguration(String analyzerId) {
	return analyzerRegistration.getInstanceById(analyzerId).getConfigurationParameters();
    }

    @Override
    public Grammar getGrammar(String analyzerId) {
	LanguageGrammar languageGrammar = analyzerRegistration.getInstanceById(analyzerId).getGrammar();
	try (InputStream grammarInput = languageGrammar.getGrammarDefinition();
		GrammarFile grammarFile = new GrammarFile(grammarInput)) {
	    ParseTreeNode parserTree = grammarFile.getParserTree();
	    return new GrammarConverter(parserTree).getGrammar();
	} catch (IOException | GrammarException e) {
	    throw new RuntimeException("Error while reading grammar.", e);
	}
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
