package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.Collection;
import java.util.UUID;

import javax.jms.JMSException;

import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryType;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers an new analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalysisService {

	public void triggerNewAnalysis(UUID projectUUID) throws JMSException;

	public Collection<AnalyzerServiceInformation> getAnalyzers();

	public Collection<RepositoryType> getRepositoryTypes();

}
