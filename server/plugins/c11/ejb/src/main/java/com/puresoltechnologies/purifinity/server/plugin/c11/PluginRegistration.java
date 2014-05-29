package com.puresoltechnologies.purifinity.server.plugin.c11;

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
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class PluginRegistration implements AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"c11.plugin", "c11.ejb", AnalyzerRemotePlugin.class,
			PluginRegistration.class);
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			C11.getInstance().getName(), C11.getInstance().getVersion(),
			JNDI_ADDRESS, "no description");

	@Inject
	private Logger logger;

	private final C11 c11 = C11.getInstance();

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
		return c11.getName();
	}

	@Override
	public Version getVersion() {
		return c11.getVersion();
	}

	@Override
	public boolean isSuitable(SourceCodeLocation source) {
		return c11.isSuitable(source);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return c11.getGrammar();
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		return c11.getImplementation(clazz);
	}

	@Override
	public CodeAnalyzer createAnalyser(SourceCodeLocation source) {
		return c11.createAnalyser(source);
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		return c11.restoreAnalyzer(file);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return c11.getAvailableConfigurationParameters();
	}

	@Override
	public <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value) {
		c11.setConfigurationParameter(parameter, value);
	}

	@Override
	public <T> T getConfigurationParameter(ConfigurationParameter<T> parameter) {
		return c11.getConfigurationParameter(parameter);
	}

	@Override
	public CodeAnalysis analyze(SourceCodeLocation sourceCodeLocation)
			throws AnalyzerException, IOException {
		return c11.analyze(sourceCodeLocation);
	}
}
