package com.puresoltechnologies.purifinity.server.plugin.java7;

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
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class PluginRegistration implements AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"java7.plugin", "java7.ejb", AnalyzerRemotePlugin.class,
			PluginRegistration.class);
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			Java.getInstance().getName(), Java.getInstance().getVersion(),
			JNDI_ADDRESS, "no description");

	@Inject
	private Logger logger;

	private final Java java = Java.getInstance();

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
		return java.getName();
	}

	@Override
	public String getVersion() {
		return java.getVersion();
	}

	@Override
	public boolean isSuitable(SourceCodeLocation source) {
		return java.isSuitable(source);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return java.getGrammar();
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		return java.getImplementation(clazz);
	}

	@Override
	public CodeAnalyzer createAnalyser(SourceCodeLocation source) {
		return java.createAnalyser(source);
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		return java.restoreAnalyzer(file);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return java.getAvailableConfigurationParameters();
	}

	@Override
	public <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value) {
		java.setConfigurationParameter(parameter, value);
	}

	@Override
	public <T> T getConfigurationParameter(ConfigurationParameter<T> parameter) {
		return java.getConfigurationParameter(parameter);
	}
}
