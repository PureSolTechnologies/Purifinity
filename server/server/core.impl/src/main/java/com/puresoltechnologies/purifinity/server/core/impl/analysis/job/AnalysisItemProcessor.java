package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.IOException;
import java.io.InputStream;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.job.PersistentStepUserData;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStore;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

@Named("AnalysisItemProcessor")
public class AnalysisItemProcessor implements ItemProcessor {

	@Inject
	private Logger logger;

	@Inject
	private FileStore fileStore;

	@Inject
	private AnalyzerServiceManager analyzerServiceManager;

	@Inject
	private StepContext stepContext;

	@Override
	public Object processItem(Object item) throws Exception {
		StoredFile storedFile = (StoredFile) item;
		FileInformation fileInformation = storedFile.getFileInformation();
		if (!fileStore.wasAnalyzed(fileInformation.getHashId())) {
			createNewAnalysis(storedFile);
		}
		PersistentStepUserData persistentUserData = (PersistentStepUserData) stepContext.getPersistentUserData();
		persistentUserData.increaseCurrentItem(1);
		return storedFile;
	}

	/**
	 * This method creates a new analysis.
	 * 
	 * @param hashId
	 * @param sourceFile
	 * @return
	 * @throws AnalyzerException
	 * @throws IOException
	 * @throws FileStoreException
	 */
	private void createNewAnalysis(StoredFile storedFile) throws IOException, FileStoreException {
		FileInformation fileInformation = storedFile.getFileInformation();
		if (fileInformation.getSize() <= 0) {
			return;
		}
		HashId hashId = fileInformation.getHashId();
		if (fileStore.wasAnalyzed(hashId)) {
			return;
		}
		SourceCodeLocation sourceCodeLocation = storedFile.getSourceCodeLocation();
		for (AnalyzerServiceInformation analyzerInformation : analyzerServiceManager.getServices()) {
			if (analyzerServiceManager.isActive(analyzerInformation.getId())) {
				ProgrammingLanguageAnalyzer instance = analyzerServiceManager
						.createProxy(analyzerInformation.getJndiName());
				if (instance.isSuitable(sourceCodeLocation)) {
					logger.info("'" + sourceCodeLocation.getHumanReadableLocationString() + "' is a suitable file for '"
							+ instance.getName() + "'.");
					try (InputStream sourceStream = sourceCodeLocation.openStream()) {
						CodeAnalysis codeAnalysis = instance.analyze(SourceCode.read(sourceStream, sourceCodeLocation),
								hashId);
						fileStore.storeAnalysis(codeAnalysis);
					}
				}
			}
		}
	}
}
