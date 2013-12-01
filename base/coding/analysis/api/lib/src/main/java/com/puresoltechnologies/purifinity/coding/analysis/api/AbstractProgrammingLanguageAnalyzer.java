package com.puresoltechnologies.purifinity.coding.analysis.api;

import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.commons.configuration.ConfigurationParameter;
import com.puresoltechnologies.purifinity.coding.lang.commons.AbstractProgrammingLanguage;

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
		@SuppressWarnings("unchecked")
		T t = (T) properties.get(parameter.getPropertyKey());
		return t != null ? t : parameter.getDefaultValue();
	}

	@Override
	public final <T> void setConfigurationParameter(
			ConfigurationParameter<T> parameter, T value) {
		properties.put(parameter.getPropertyKey(), value);
	}
}
