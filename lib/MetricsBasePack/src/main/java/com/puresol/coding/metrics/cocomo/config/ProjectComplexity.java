package com.puresol.coding.metrics.cocomo.config;

import com.puresol.coding.metrics.cocomo.Complexity;
import com.puresol.config.PropertyDescription;

public class ProjectComplexity implements PropertyDescription<Complexity> {

    @Override
    public String getPropertyName() {
	return "cocomo.project.complexity";
    }

    @Override
    public String getDisplayName() {
	return "Project Complexity";
    }

    @Override
    public String getDescription() {
	return "Specify here the complexity of the project.";
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
