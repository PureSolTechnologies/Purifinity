package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

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
public class FortranServiceRegistration extends AbstractServiceRegistration
		implements AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"fortran2008.plugin", "fortran2008.ejb",
			ProgrammingLanguageAnalyzer.class, Fortran.class);
	private static final AnalyzerServiceInformation INFORMATION = new AnalyzerServiceInformation(
			Fortran.NAME, Fortran.VERSION, Fortran.PLUGIN_VERSION,
			JNDI_ADDRESS,
			"This is a Fortran 2008 programming language analyzer.",
			"/fortran2008.ui/index", "/fortran2008.ui/config",
			"/fortran2008.ui/project", "/fortran2008.ui/run");

	@PostConstruct
	public void registration() {
		register(AnalyzerServiceManagerRemote.class,
				AnalyzerServiceManagerRemote.JNDI_NAME,
				FortranPlugin.INFORMATION, JNDI_ADDRESS, INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(AnalyzerServiceManagerRemote.class,
				AnalyzerServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return Fortran.NAME;
	}
}
