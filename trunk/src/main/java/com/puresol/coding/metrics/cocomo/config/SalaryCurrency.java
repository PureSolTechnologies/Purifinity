package com.puresol.coding.metrics.cocomo.config;

import javax.i18n4java.Translator;

import com.puresol.config.properties.PropertyDescription;

public class SalaryCurrency implements PropertyDescription<String> {

	private static final Translator translator = Translator
			.getTranslator(SalaryCurrency.class);

	@Override
	public String getPropertyName() {
		return "cocomo.salary.currency";
	}

	@Override
	public String getDisplayName() {
		return translator.i18n("Currency of Salary");
	}

	@Override
	public String getDescription() {
		return translator
				.i18n("This is the salary's currency to be put into CoCoMo calcualtions.\n"
						+ "The currency can be specified as a string.");
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	public String getDefaultValue() {
		return "USD";
	}

}
