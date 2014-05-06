package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Local;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface AnalyzerPluginService extends
		PluginService<AnalyzerInformation> {

}
