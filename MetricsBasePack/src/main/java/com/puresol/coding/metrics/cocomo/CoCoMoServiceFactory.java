package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.metrics.cocomo.config.ProjectComplexity;
import com.puresol.coding.metrics.cocomo.config.SalaryCurrency;
import com.puresol.coding.metrics.cocomo.config.YearlyDeveloperSalary;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.config.Configuration;
import com.puresol.config.PropertyDescription;
import com.puresol.utils.EnumUtilities;

public class CoCoMoServiceFactory implements ProjectEvaluatorFactory {

    @Override
    public ProjectEvaluator create(ProjectAnalyzer projectAnalyser,
	    Configuration configuration) {
	CoCoMo cocomo = new CoCoMo(projectAnalyser);
	setConfiguration(cocomo, configuration);
	return cocomo;
    }

    private void setConfiguration(CoCoMo cocomo, Configuration configuration) {
	String complexity = "";
	String salary = "";
	String currency = "";
	for (PropertyDescription<?> description : CoCoMo.CONFIGURATION_PROPERTIES) {
	    if (description.getClass().equals(ProjectComplexity.class)) {
		complexity = configuration.getProperty(
			description.getPropertyName(),
			description.getDefaultValue().toString()).toString();
	    } else if (description.getClass().equals(SalaryCurrency.class)) {
		currency = configuration.getProperty(
			description.getPropertyName(),
			(String) description.getDefaultValue());
	    } else if (description.getClass().equals(
		    YearlyDeveloperSalary.class)) {
		salary = configuration.getProperty(
			description.getPropertyName(),
			description.getDefaultValue().toString()).toString();
	    }
	}
	cocomo.setComplexity(EnumUtilities.findEnumConstante(Complexity.class,
		complexity));
	cocomo.setAverageSalary(Integer.valueOf(salary), currency);
    }

    @Override
    public Class<? extends ProjectEvaluator> getProjectEvaluatorClass() {
	return CoCoMo.class;
    }

    @Override
    public String getDescription() {
	return CoCoMo.DESCRIPTION;
    }

    @Override
    public String getName() {
	return CoCoMo.NAME;
    }

    /**
     * {@inheritDoc}
     * 
     * For CoCoMo there is no quality characteristics assigned. It's a pure
     * economic evaluation.
     */
    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return new ArrayList<QualityCharacteristic>();
    }

}
