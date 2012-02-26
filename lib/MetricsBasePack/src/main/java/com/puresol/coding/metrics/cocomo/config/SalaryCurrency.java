package com.puresol.coding.metrics.cocomo.config;

import com.puresol.config.PropertyDescription;

public class SalaryCurrency implements PropertyDescription<String> {

    @Override
    public String getPropertyName() {
	return "cocomo.salary.currency";
    }

    @Override
    public String getDisplayName() {
	return "Currency of Salary";
    }

    @Override
    public String getDescription() {
	return "This is the salary's currency to be put into CoCoMo calcualtions.\n"
		+ "The currency can be specified as a string.";
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
