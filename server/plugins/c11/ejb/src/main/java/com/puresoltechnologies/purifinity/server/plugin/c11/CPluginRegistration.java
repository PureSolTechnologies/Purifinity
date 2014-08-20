package com.puresoltechnologies.purifinity.server.plugin.c11;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class CPluginRegistration extends AbstractServiceRegistration implements
		AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"c11.plugin", "c11.ejb", ProgrammingLanguageAnalyzer.class,
			C11.class);
	private static final AnalyzerServiceInformation INFORMATION = new AnalyzerServiceInformation(
			C11.NAME, C11.VERSION, C11.PLUGIN_VERSION, JNDI_ADDRESS,
			"This is a C11 programming language analyzer.");

	@PostConstruct
	public void registration() {
		register(AnalyzerServiceManagerRemote.class,
				AnalyzerServiceManagerRemote.JNDI_NAME, C11Plugin.INFORMATION,
				JNDI_ADDRESS, INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(AnalyzerServiceManagerRemote.class,
				AnalyzerServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return C11.NAME;
	}
}
