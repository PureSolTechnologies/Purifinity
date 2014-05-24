package com.puresoltechnologies.purifinity.server.core.impl.analysis.plugins;

import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;

@Singleton
public class AnalyzerPluginServiceImpl extends
		AbstractPluginService<AnalyzerInformation> implements
		AnalyzerPluginService, AnalyzerPluginServiceRemote {

	public AnalyzerPluginServiceImpl() {
		super("Analyzer Plugin Service");
	}

	@Override
	public AnalyzerRemotePlugin createInstance(String jndi)
			throws NamingException {
		InitialContext initialContext = new InitialContext();
		return (AnalyzerRemotePlugin) initialContext.lookup(jndi);
	}

}
