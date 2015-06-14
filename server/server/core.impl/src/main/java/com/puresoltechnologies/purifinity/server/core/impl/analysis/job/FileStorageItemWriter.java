package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("FileStorageItemWriter")
public class FileStorageItemWriter extends AbstractItemWriter {

	@Inject
	private JobContext jobContext;

	@Override
	public void writeItems(List<Object> items) throws Exception {
		AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext
				.getTransientUserData();

		for (Object item : items) {
			StoredFile storedFile = (StoredFile) item;
			if (storedFile.getHashId() != null) {
				analysisJobContext.addStoredFile(
						storedFile.getSourceCodeLocation(),
						storedFile.getHashId());
			}
		}
	}

}
