package com.puresoltechnologies.purifinity.server.plugin.java7;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AbstractAnalyzerServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
@EJBFacade
public class JavaServiceRegistration extends
		AbstractAnalyzerServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"java7.plugin", "java7.ejb", ProgrammingLanguageAnalyzer.class,
			Java.class);
	private static final AnalyzerServiceInformation INFORMATION = new AnalyzerServiceInformation(
			Java.ID, Java.NAME, Java.VERSION, Java.PLUGIN_VERSION,
			JNDI_ADDRESS, "This is a Java 7 programming language analyzer.",
			"/java7.ui/index.jsf", "/java7.ui/config.jsf",
			"/java7.ui/project.jsf", "/java7.ui/run.jsf");

	@PostConstruct
	public void registraion() {
		register(AnalyzerServiceManagerRemote.class,
				AnalyzerServiceManagerRemote.JNDI_NAME, JavaPlugin.INFORMATION,
				JNDI_ADDRESS, INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(AnalyzerServiceManagerRemote.class,
				AnalyzerServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return Java.NAME;
	}

	@Override
	public AnalyzerServiceInformation getServiceInformation() {
		return INFORMATION;
	}
}
