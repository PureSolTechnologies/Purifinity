package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.parsers.source.RepositoryLocation;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.common.job.PersistentStepUserData;
import com.puresoltechnologies.purifinity.server.core.api.repositories.RepositoryServiceManager;

@Named("FileStorageItemReader")
public class FileStorageItemReader extends AbstractItemReader {

	@Inject
	private Logger logger;

	@Inject
	private RepositoryServiceManager repositoryServiceManager;

	@Inject
	private JobContext jobContext;

	@Inject
	private StepContext stepContext;

	private FileStorageCheckpoint checkpoint;

	@Override
	public void open(Serializable checkpoint) throws Exception {
		super.open(checkpoint);
		logger.info("Item reader started for job " + jobContext.getJobName()
				+ "...");
		if (checkpoint != null) {
			this.checkpoint = (FileStorageCheckpoint) checkpoint;
		} else {
			AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext
					.getTransientUserData();
			AnalysisProject analysisProject = analysisJobContext
					.getAnalysisProject();
			AnalysisProjectSettings projectSettings = analysisProject
					.getSettings();
			RepositoryLocation repository = repositoryServiceManager
					.createFromSerialization(projectSettings.getRepository());
			List<SourceCodeLocation> sourceCodeLocations = repository
					.getSourceCodes(projectSettings
							.getFileSearchConfiguration());
			this.checkpoint = new FileStorageCheckpoint(sourceCodeLocations);
		}
		stepContext
				.setPersistentUserData(new PersistentStepUserData(
						"File Storage",
						"The project is currently searched for all relevant files and these are stored into database.",
						this.checkpoint.getTotalItemCount()));
	}

	@Override
	public Object readItem() throws Exception {
		return checkpoint.getNext();
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return checkpoint;
	}

	@Override
	public void close() throws Exception {
		super.close();
		logger.info("Item reader closed for job " + jobContext.getJobName()
				+ ".");
	}
}
