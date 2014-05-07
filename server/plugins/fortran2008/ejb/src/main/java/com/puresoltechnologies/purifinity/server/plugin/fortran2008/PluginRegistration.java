package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.framework.lang.fortran2008.Fortran;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class PluginRegistration implements AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"fortran2008.plugin", "fortran2008.ejb",
			AnalyzerRemotePlugin.class, PluginRegistration.class);
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			Fortran.getInstance().getName(),
			Fortran.getInstance().getVersion(), "no description");

	@Inject
	private Logger logger;

	private Fortran fortran;

	@PostConstruct
	public void registraion() throws InterruptedException {
		logger.info("Try to regiser Java 7 plugin...");
		int retried = 0;
		boolean registered = false;
		while (retried < 10) {
			try {
				InitialContext context = new InitialContext();
				AnalyzerPluginServiceRemote registrator = (AnalyzerPluginServiceRemote) context
						.lookup(AnalyzerPluginServiceRemote.JNDI_NAME);
				registrator.registerService(JNDI_ADDRESS, INFORMATION,
						new Properties());
				registered = true;
				break;
			} catch (NamingException e) {
				Thread.sleep(1000);
				retried++;
			}
		}
		if (!registered) {
			throw new IllegalStateException("Could not register plugin.");
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSuitable(SourceCodeLocation source) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LanguageGrammar getGrammar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeAnalyzer createAnalyser(SourceCodeLocation source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T getConfigurationParameter(ConfigurationParameter<T> parameter) {
		// TODO Auto-generated method stub
		return null;
	}
}
