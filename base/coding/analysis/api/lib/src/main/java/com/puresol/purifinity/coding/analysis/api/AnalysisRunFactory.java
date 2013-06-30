package com.puresol.purifinity.coding.analysis.api;

import java.io.File;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.UUID;

import com.puresol.purifinity.utils.FileSearchConfiguration;

public abstract class AnalysisRunFactory {

	private static AnalysisRunFactory instance = null;

	public static AnalysisRunFactory getInstance() {
		if (instance == null) {
			loadInstance();
		}
		return instance;
	}

	private static synchronized void loadInstance() {
		if (instance == null) {
			ServiceLoader<AnalysisRunFactory> loader = ServiceLoader
					.load(AnalysisRunFactory.class);
			Iterator<AnalysisRunFactory> iterator = loader.iterator();
			if (!iterator.hasNext()) {
				throw new IllegalStateException(
						"No AnalysisRun implementation was found.");
			}
			instance = iterator.next();
			if (iterator.hasNext()) {
				throw new IllegalStateException(
						"Too many AnalysisRun implementations were found.");
			}
		}
	}

	public abstract AnalysisRun create(File runDirectory,
			AnalysisProjectInformation analysisInformation, UUID randomUUID,
			RepositoryLocation directoryRepositoryLocation,
			FileSearchConfiguration fileSearchConfiguration);

}
