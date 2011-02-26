package com.puresol.coding.metrics.cocomo.config;

import javax.i18n4java.Translator;

import com.puresol.config.properties.PropertyDescription;

public class YearlyDeveloperSalary implements PropertyDescription<Integer> {

	private static final Translator translator = Translator
			.getTranslator(YearlyDeveloperSalary.class);

	@Override
	public String getPropertyName() {
		return "cocomo.salary.amount";
	}

	@Override
	public String getDisplayName() {
		return translator.i18n("Yearly Developer Salary");
	}

	@Override
	public String getDescription() {
		return translator
				.i18n("This is the yearly salary for a developer to be put into CoCoMo calcualtions.\n"
						+ "The amount can be specified as an integer value.");
	}

	@Override
	public Class<Integer> getType() {
		return Integer.class;
	}

	@Override
	public Integer getDefaultValue() {
		return 56286; // in USD
	}

}
