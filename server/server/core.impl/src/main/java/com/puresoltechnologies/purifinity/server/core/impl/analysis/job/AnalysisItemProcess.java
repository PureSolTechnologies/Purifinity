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
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.thinkaurelius.groovyshadedasm.tree.analysis.AnalyzerException;

@Named("AnalysisItemProcess")
public class AnalysisItemProcess implements ItemProcessor {

	@Inject
	private Logger logger;

	@Inject
	private FileStoreService fileStore;

	@Inject
	private AnalyzerServiceManager analyzerServiceManager;

	@Inject
	private StepContext stepContext;

	@Override
	public Object processItem(Object item) throws Exception {
		StoredFile storedFile = (StoredFile) item;
		if (!fileStore.wasAnalyzed(storedFile.getHashId())) {
			createNewAnalysis(storedFile);
		}
		PersistentStepUserData persistentUserData = (PersistentStepUserData) stepContext
				.getPersistentUserData();
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
	private void createNewAnalysis(StoredFile storedFile) throws IOException,
			FileStoreException {
		HashId hashId = storedFile.getHashId();
		SourceCodeLocation sourceCodeLocation = storedFile
				.getSourceCodeLocation();
		for (AnalyzerServiceInformation analyzerInformation : analyzerServiceManager
				.getServices()) {
			if (analyzerServiceManager.isActive(analyzerInformation.getId())) {
				ProgrammingLanguageAnalyzer instance = analyzerServiceManager
						.createProxy(analyzerInformation.getJndiName());
				if (instance.isSuitable(sourceCodeLocation)) {
					logger.info("'"
							+ sourceCodeLocation
									.getHumanReadableLocationString()
							+ "' is a suitable file for '" + instance.getName()
							+ "'.");
					try (InputStream sourceStream = sourceCodeLocation
							.openStream()) {
						CodeAnalysis codeAnalysis = instance
								.analyze(SourceCode.read(sourceStream,
										sourceCodeLocation), hashId);
						fileStore.storeAnalysis(codeAnalysis);
					}
				}
			}
		}
	}
}
