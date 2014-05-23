package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.Collection;
import java.util.UUID;

import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.framework.store.api.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers an new analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalysisService {

	public void triggerNewAnalysis(UUID projectUUID)
			throws AnalysisStoreException, AnalyzerException;

	public Collection<AnalyzerInformation> getAnalyzers();

	public Collection<RepositoryType> getRepositoryTypes();

}
