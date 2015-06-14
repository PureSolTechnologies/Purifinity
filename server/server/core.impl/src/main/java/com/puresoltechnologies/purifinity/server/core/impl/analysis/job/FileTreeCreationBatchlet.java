package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.Map;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalysisRunFileTree;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreService;

@Named("FileTreeCreationBatchlet")
public class FileTreeCreationBatchlet implements Batchlet {

	@Inject
	private Logger logger;

	@Inject
	private AnalysisStoreService analysisStoreService;

	@Inject
	private JobContext jobContext;

	@Override
	public String process() throws Exception {
		logger.info("Create and store analysis run file tree...");

		AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext
				.getTransientUserData();
		AnalysisProject analysisProject = analysisJobContext
				.getAnalysisProject();
		AnalysisRunInformation analysisRunInformation = analysisJobContext
				.getAnalysisRunInformation();

		AnalysisProjectSettings projectSettings = analysisProject.getSettings();
		Map<SourceCodeLocation, HashId> storedSources = analysisJobContext
				.getStoredFiles();

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
