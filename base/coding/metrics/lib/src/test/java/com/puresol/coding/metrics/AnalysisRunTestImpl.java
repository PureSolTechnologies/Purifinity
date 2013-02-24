package com.puresol.coding.metrics;

import java.io.File;
import java.util.UUID;

import com.puresol.coding.analysis.api.AnalysisProjectInformation;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalysisRunFactory;
import com.puresol.coding.analysis.api.RepositoryLocation;
import com.puresol.utils.FileSearchConfiguration;

public class AnalysisRunTestImpl extends AnalysisRunFactory {

	@Override
	public AnalysisRun create(File runDirectory,
			AnalysisProjectInformation analysisInformation, UUID randomUUID,
			RepositoryLocation directoryRepositoryLocation,
			FileSearchConfiguration fileSearchConfiguration) {
		return null;
	}

}
