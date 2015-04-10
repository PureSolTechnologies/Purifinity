package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.Collection;
import java.util.Set;

import javax.jms.JMSException;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers new analyses.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalysisService {

	public void triggerNewRun(String projectId) throws JMSException;

	public void abortCurrentRun(String projectId);

	public Collection<AnalyzerServiceInformation> getAnalyzers();

	public AnalyzerServiceInformation getAnalyzer(String analyzerId);

	public Set<ConfigurationParameter<?>> getConfiguration(String analyzerId);

	public boolean isEnabled(String analyzerId);

	public void setActive(String analyzerId, boolean enabled);

}
