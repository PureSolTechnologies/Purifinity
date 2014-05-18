package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.Collection;

import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers an new analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalysisService {

	public void triggerNewAnalysis();

	public Collection<AnalyzerInformation> getAnalyzers();

	public Collection<RepositoryType> getRepositoryTypes();

}
