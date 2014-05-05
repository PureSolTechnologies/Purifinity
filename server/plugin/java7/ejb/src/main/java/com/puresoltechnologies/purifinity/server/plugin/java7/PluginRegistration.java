package com.puresoltechnologies.purifinity.server.plugin.java7;

import java.io.File;
import java.io.IOException;
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
import com.puresoltechnologies.purifinity.framework.lang.java7.Java;
import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerRegistrationRemote;
import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class PluginRegistration implements AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"plugin.java7.app", "plugin.java7.ejb", AnalyzerRemotePlugin.class,
			PluginRegistration.class);
	// "java:global/plugin.java7.app/plugin.java7.ejb/PluginRegistration!com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerRemotePlugin";
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			Java.getInstance().getName(), Java.getInstance().getVersion(),
			"no description");

	@Inject
	private Logger logger;

	private Java java;

	@PostConstruct
	public void registraion() throws InterruptedException {
		logger.info("Try to regiser Java 7 plugin...");
		int retried = 0;
		boolean registered = false;
		while (retried < 10) {
			try {
				InitialContext context = new InitialContext();
				AnalyzerRegistrationRemote registrator = (AnalyzerRegistrationRemote) context
						.lookup("java:global/analysisservice.app/analysisservice.core.impl/AnalyzerRegistrationImpl!com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerRegistrationRemote");
				registrator.registerRemote(JNDI_ADDRESS, INFORMATION);
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
