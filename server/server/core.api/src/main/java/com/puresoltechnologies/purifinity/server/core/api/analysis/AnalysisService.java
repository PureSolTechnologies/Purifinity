package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.Collection;

import javax.jms.JMSException;

import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.domain.repositories.RepositoryTypeServiceInformation;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers an new analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalysisService {

    public void triggerNewRun(String projectId) throws JMSException;

    public Collection<AnalyzerServiceInformation> getAnalyzers();

    public Collection<RepositoryTypeServiceInformation> getRepositoryTypes();

}
