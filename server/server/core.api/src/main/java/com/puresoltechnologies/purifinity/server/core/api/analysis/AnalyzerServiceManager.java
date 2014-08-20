package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Local;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface AnalyzerServiceManager extends
	ServiceManager<AnalyzerServiceInformation> {

    public ProgrammingLanguageAnalyzer createInstance(String jndi);

}
