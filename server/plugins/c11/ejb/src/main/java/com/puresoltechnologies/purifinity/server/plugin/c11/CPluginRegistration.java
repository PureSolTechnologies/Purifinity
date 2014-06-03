package com.puresoltechnologies.purifinity.server.plugin.c11;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.commons.misc.ConfigurationParameter;
import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
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
			"c11.plugin", "c11.ejb", AnalyzerRemotePlugin.class,
			CPluginRegistration.class);
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			C11.getInstance().getName(), C11.getInstance().getVersion(),
			JNDI_ADDRESS, "no description");

	private final C11 c11 = C11.getInstance();

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
