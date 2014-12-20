package com.puresoltechnologies.purifinity.analysis.spi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.math.ConfigurationParameter;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;

/**
 * This class is an abstract implementation of
 * {@link ProgrammingLanguageAnalyzer}.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractProgrammingLanguageAnalyzer extends
		AbstractProgrammingLanguage implements ProgrammingLanguageAnalyzer {

	private final Map<String, Object> properties = new HashMap<>();

	protected AbstractProgrammingLanguageAnalyzer(String name, String version) {
		super(name, version);
	}

	@Override
	public final <T> T getConfigurationParameter(
			ConfigurationParameter<T> parameter) {
		if (!getAvailableConfigurationParameters().contains(parameter)) {
			throw new IllegalArgumentException("The parameter '" + parameter
					+ "' is not known.");
		}
		@SuppressWarnings("unchecked")
		T t = (T) properties.get(parameter.getPropertyKey());
		return t != null ? t : parameter.getDefaultValue();
	}

	@Override
	public final <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value) {
		if (!getAvailableConfigurationParameters().contains(parameter)) {
			throw new IllegalArgumentException("The parameter '" + parameter
					+ "' is not known.");
		}
		properties.put(parameter.getPropertyKey(), value);
	}

	@Override
	public CodeAnalysis analyze(SourceCodeLocation sourceCodeLocation)
			throws AnalyzerException, IOException {
		CodeAnalyzer analyser = createAnalyser(sourceCodeLocation);
		analyser.analyze();
		return analyser.getAnalysis();
	}
}
