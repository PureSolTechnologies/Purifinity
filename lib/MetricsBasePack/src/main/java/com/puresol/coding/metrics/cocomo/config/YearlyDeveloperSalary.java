package com.puresol.coding.metrics.cocomo.config;

import com.puresol.config.PropertyDescription;

public class YearlyDeveloperSalary implements PropertyDescription<Integer> {

    @Override
    public String getPropertyName() {
	return "cocomo.salary.amount";
    }

    @Override
    public String getDisplayName() {
	return "Yearly Developer Salary";
    }

    @Override
    public String getDescription() {
	return "This is the yearly salary for a developer to be put into CoCoMo calcualtions.\n"
		+ "The amount can be specified as an integer value.";
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
