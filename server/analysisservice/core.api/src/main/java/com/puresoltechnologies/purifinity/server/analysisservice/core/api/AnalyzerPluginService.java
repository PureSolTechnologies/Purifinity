package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import javax.ejb.Local;

import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface AnalyzerPluginService extends
		PluginService<AnalyzerInformation> {

}
