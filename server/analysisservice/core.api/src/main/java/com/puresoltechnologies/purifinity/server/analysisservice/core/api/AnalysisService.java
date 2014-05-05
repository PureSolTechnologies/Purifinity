package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import java.util.Collection;

import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers an new analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalysisService {

	public void triggerNewAnalysis();

	public Collection<AnalyzerInformation> getAnalyzers();

}
