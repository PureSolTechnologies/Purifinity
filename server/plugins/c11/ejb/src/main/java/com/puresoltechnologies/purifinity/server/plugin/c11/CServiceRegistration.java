package com.puresoltechnologies.purifinity.server.plugin.c11;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.analysis.domain.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AbstractAnalyzerServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
@EJBFacade
public class CServiceRegistration extends AbstractAnalyzerServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"c11.plugin", "c11.ejb", ProgrammingLanguageAnalyzer.class,
			C11.class);
	private static final AnalyzerServiceInformation INFORMATION = new AnalyzerServiceInformation(
			C11.ID, C11.NAME, C11.VERSION, C11.PLUGIN_VERSION, JNDI_ADDRESS,
			"This is a C11 programming language analyzer.", C11.PARAMETERS,
			"/c11.ui/index.jsf", "/c11.ui/project.jsf", "/c11.ui/run.jsf");

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

	@Override
	public AnalyzerServiceInformation getServiceInformation() {
		return INFORMATION;
	}
}
