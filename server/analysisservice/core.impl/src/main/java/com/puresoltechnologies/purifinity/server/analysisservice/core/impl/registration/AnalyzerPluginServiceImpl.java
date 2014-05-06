package com.puresoltechnologies.purifinity.server.analysisservice.core.impl.registration;

import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginService;

@Singleton
public class AnalyzerPluginServiceImpl extends
		AbstractPluginService<AnalyzerInformation> implements
		AnalyzerPluginService, AnalyzerPluginServiceRemote {

	public AnalyzerPluginServiceImpl() {
		super("Analyzer Plugin Service");
	}

}
