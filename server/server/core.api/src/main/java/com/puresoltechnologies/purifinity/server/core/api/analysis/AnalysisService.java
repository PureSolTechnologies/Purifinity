package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.util.Collection;
import java.util.List;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.core.api.analysis.jobs.PurifinityJobStates;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.AnalysisStoreException;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

/**
 * This is the interface for analysis service. This service provides information
 * about the analyzers and also triggers new analyses.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface AnalysisService {

	public void triggerRunJob(String projectId) throws AnalysisStoreException;

	public void abortRun(long jobId);

	public PurifinityJobStates getJobStates();

	public Collection<AnalyzerServiceInformation> getAnalyzers();

	public AnalyzerServiceInformation getAnalyzer(String analyzerId);

	public List<ConfigurationParameter<?>> getConfiguration(String analyzerId);

	public boolean isEnabled(String analyzerId);

	public void setActive(String analyzerId, boolean enabled);

}
