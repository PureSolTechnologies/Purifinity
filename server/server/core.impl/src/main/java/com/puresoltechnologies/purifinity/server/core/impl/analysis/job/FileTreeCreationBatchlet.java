package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.Map;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.common.job.PersistentStepUserData;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.ProjectManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;

@Named("FileTreeCreationBatchlet")
public class FileTreeCreationBatchlet implements Batchlet {

	@Inject
	private Logger logger;

	@Inject
	private ProjectManager analysisStoreService;

	@Inject
	private JobContext jobContext;

	@Inject
	private StepContext stepContext;

	@Override
	public String process() throws Exception {
		logger.info("Create and store analysis run file tree...");

		PersistentStepUserData persistentUserData = new PersistentStepUserData(
				"Create and Store File Tree",
				"For the project the complete file tree is generated and stored in database.",
				2);
		stepContext.setPersistentUserData(persistentUserData);

		AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext
				.getTransientUserData();
		AnalysisProject analysisProject = analysisJobContext
				.getAnalysisProject();
		AnalysisRunInformation analysisRunInformation = analysisJobContext
				.getAnalysisRunInformation();

		AnalysisProjectSettings projectSettings = analysisProject.getSettings();
		Map<SourceCodeLocation, FileInformation> storedSources = analysisJobContext
				.getStoredFiles();
		persistentUserData.increaseCurrentItem(1);
		AnalysisRunFileTree analysisRunFileTree = analysisStoreService
				.createAndStoreFileAndContentTree(
						analysisRunInformation.getProjectId(),
						analysisRunInformation.getRunId(),
						projectSettings.getName(), storedSources);

		analysisJobContext.setAnalysisRunFileTree(analysisRunFileTree);
		logger.info("analysis run file tree created and stored.");
		return "SUCCESS";
	}

	@Override
	public void stop() throws Exception {
		// unfortunately not supported...
	}

}
