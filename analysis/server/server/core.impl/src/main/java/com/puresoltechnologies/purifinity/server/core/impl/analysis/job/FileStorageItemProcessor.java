package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.IOException;
import java.io.InputStream;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectSettings;
import com.puresoltechnologies.purifinity.server.common.job.PersistentStepUserData;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.server.systemmonitor.core.api.events.EventLoggerRemote;

/**
 * This message driven bean is the storage stage of the analysis. Here the
 * project files are read and stored into the file store.
 * 
 * @author Rick-Rainer Ludwig
 */
@Named("FileStorageItemProcessor")
public class FileStorageItemProcessor implements ItemProcessor {

    @Inject
    private EventLoggerRemote eventLogger;

    @Inject
    private FileStore fileStore;

    @Inject
    private JobContext jobContext;

    @Inject
    private StepContext stepContext;

    @Override
    public Object processItem(Object item) throws Exception {
	SourceCodeLocation sourceCodeLocation = (SourceCodeLocation) item;

	AnalysisJobContext analysisJobContext = (AnalysisJobContext) jobContext.getTransientUserData();
	AnalysisProject analysisProject = analysisJobContext.getAnalysisProject();
	AnalysisProjectSettings projectSettings = analysisProject.getSettings();

	FileInformation fileInformation = storeFile(projectSettings, sourceCodeLocation);
	PersistentStepUserData persistentUserData = (PersistentStepUserData) stepContext.getPersistentUserData();
	persistentUserData.increaseCurrentItem(1);
	stepContext.setPersistentUserData(persistentUserData);
	return new StoredFile(sourceCodeLocation, fileInformation);
    }

    /**
     * 
     * Stores a single file in database.
     * 
     * @param projectSettings
     * @param repository
     * @param sourceCodeLocation
     * @return
     */
    private FileInformation storeFile(AnalysisProjectSettings projectSettings, SourceCodeLocation sourceCodeLocation) {
	try (InputStream stream = sourceCodeLocation.openStream()) {
	    FileInformation fileInformation = fileStore.storeRawFile(stream);
	    if (fileInformation != null) {
		HashId hashId = fileInformation.getHashId();
		eventLogger.logEvent(AnalysisEvents.createRawFileStoredEvent(sourceCodeLocation.getInternalLocation(),
			hashId, projectSettings.getName(), sourceCodeLocation.getHumanReadableLocationString()));
	    } else {
		eventLogger
			.logEvent(AnalysisEvents.createRawFileNotStoredEvent(sourceCodeLocation.getInternalLocation(),
				projectSettings.getName(), sourceCodeLocation.getHumanReadableLocationString()));
	    }
	    return fileInformation;
	} catch (FileStoreException | IOException e) {
	    eventLogger.logEvent(AnalysisEvents.createRawFileStoreError(sourceCodeLocation.getInternalLocation(),
		    projectSettings.getName(), sourceCodeLocation.getHumanReadableLocationString(), e));
	    throw new RuntimeException(
		    "Error storing file '" + sourceCodeLocation.getHumanReadableLocationString() + "'.");
	}
    }
}
