package com.puresol.purifinity.coding.metrics;

import java.io.File;
import java.util.UUID;

import com.puresol.purifinity.coding.analysis.api.AnalysisProjectInformation;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.AnalysisRunFactory;
import com.puresol.purifinity.coding.analysis.api.RepositoryLocation;
import com.puresol.purifinity.utils.FileSearchConfiguration;

public class AnalysisRunTestImpl extends AnalysisRunFactory {

	@Override
	public AnalysisRun create(File runDirectory,
			AnalysisProjectInformation analysisInformation, UUID randomUUID,
			RepositoryLocation directoryRepositoryLocation,
			FileSearchConfiguration fileSearchConfiguration) {
		return null;
	}

}
