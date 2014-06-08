package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

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
public class FortranPluginRegistration extends AbstractPluginRegistration
		implements AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"fortran2008.plugin", "fortran2008.ejb",
			ProgrammingLanguageAnalyzer.class, Fortran.class);
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			Fortran.NAME, Fortran.VERSION, JNDI_ADDRESS, "no description");

	private final Fortran fortran = new Fortran();

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
		return fortran.getName();
	}
}