package com.puresol.coding.metrics.cocomo.config;

import javax.i18n4java.Translator;

import com.puresol.coding.metrics.cocomo.Complexity;
import com.puresol.config.properties.PropertyDescription;

public class ProjectComplexity implements PropertyDescription<Complexity> {

	private static final Translator translator = Translator
			.getTranslator(ProjectComplexity.class);

	@Override
	public String getPropertyName() {
		return "cocomo.project.complexity";
	}

	@Override
	public String getDisplayName() {
		return translator.i18n("Project Complexity");
	}

	@Override
	public String getDescription() {
		return translator.i18n("Specify here the complexity of the project.");
	}

	@Override
	public Class<Complexity> getType() {
		return Complexity.class;
	}

	@Override
	public Complexity getDefaultValue() {
		return Complexity.LOW;
	}

}
