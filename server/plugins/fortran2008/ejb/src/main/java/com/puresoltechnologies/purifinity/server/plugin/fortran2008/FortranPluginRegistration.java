package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

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
public class FortranPluginRegistration extends AbstractPluginRegistration implements
		AnalyzerRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"fortran2008.plugin", "fortran2008.ejb",
			AnalyzerRemotePlugin.class, FortranPluginRegistration.class);
	private static final AnalyzerInformation INFORMATION = new AnalyzerInformation(
			Fortran.getInstance().getName(),
			Fortran.getInstance().getVersion(), JNDI_ADDRESS, "no description");

	private final Fortran fortran = Fortran.getInstance();

	@PostConstruct
	public void registration() throws InterruptedException, NamingException {
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
		return fortran.getName();
	}

	@Override
	public Version getVersion() {
		return fortran.getVersion();
	}

	@Override
	public boolean isSuitable(SourceCodeLocation source) {
		return fortran.isSuitable(source);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return fortran.getGrammar();
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		return fortran.getImplementation(null);
	}

	@Override
	public CodeAnalyzer createAnalyser(SourceCodeLocation source) {
		return fortran.createAnalyser(source);
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		return fortran.restoreAnalyzer(file);
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return fortran.getAvailableConfigurationParameters();
	}

	@Override
	public <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value) {
		fortran.setConfigurationParameter(parameter, value);
	}

	@Override
	public <T> T getConfigurationParameter(ConfigurationParameter<T> parameter) {
		return fortran.getConfigurationParameter(parameter);
	}

	@Override
	public CodeAnalysis analyze(SourceCodeLocation sourceCodeLocation)
			throws AnalyzerException, IOException {
		return fortran.analyze(sourceCodeLocation);
	}
}
