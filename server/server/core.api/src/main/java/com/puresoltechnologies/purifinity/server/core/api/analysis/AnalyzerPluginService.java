package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Local;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerPluginInformation;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface AnalyzerPluginService extends
	PluginService<AnalyzerPluginInformation> {

    public ProgrammingLanguageAnalyzer createInstance(String jndi);

}
