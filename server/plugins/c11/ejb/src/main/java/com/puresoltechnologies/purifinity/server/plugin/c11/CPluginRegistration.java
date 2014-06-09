package com.puresoltechnologies.purifinity.server.plugin.c11;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginRegistration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class CPluginRegistration extends AbstractPluginRegistration implements
		AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"c11.plugin", "c11.ejb", ProgrammingLanguageAnalyzer.class,
			C11.class);
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			C11.NAME, C11.VERSION, JNDI_ADDRESS,
			"This is a C11 programming language analyzer.");

	private final C11 c11 = new C11();

	@PostConstruct
	public void registration() {
		register(AnalyzerPluginServiceRemote.class,
				AnalyzerPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS,
				INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(AnalyzerPluginServiceRemote.class,
				AnalyzerPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return c11.getName();
	}
}
