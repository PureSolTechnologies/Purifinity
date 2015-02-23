package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.Collection;

import javax.jms.JMSException;

import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers new analyses.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalysisService {

    public void triggerNewRun(String projectId) throws JMSException;

    public Collection<AnalyzerServiceInformation> getAnalyzers();

}
