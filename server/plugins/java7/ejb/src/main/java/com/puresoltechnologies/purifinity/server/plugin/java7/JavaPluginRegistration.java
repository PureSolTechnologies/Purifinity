package com.puresoltechnologies.purifinity.server.plugin.java7;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.NamingException;

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
public class JavaPluginRegistration extends AbstractPluginRegistration implements
		AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"java7.plugin", "java7.ejb", AnalyzerRemotePlugin.class,
			JavaPluginRegistration.class);
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			Java.getInstance().getName(), Java.getInstance().getVersion(),
			JNDI_ADDRESS, "no description");

	private final Java java = Java.getInstance();

	@PostConstruct
	public void registraion() throws InterruptedException, NamingException {
		register(AnalyzerPluginServiceRemote.class,
				AnalyzerPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS,
				INFORMATION);
	}

	@PreDestroy
	public void unregistration() throws InterruptedException, NamingException {
		unregister(AnalyzerPluginServiceRemote.class,
				AnalyzerPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return java.getName();
	}

	@Override
	public Version getVersion() {
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

	@Override
	public CodeAnalysis analyze(SourceCodeLocation sourceCodeLocation)
			throws AnalyzerException, IOException {
		return java.analyze(sourceCodeLocation);
	}
}
