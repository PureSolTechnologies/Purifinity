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
import com.puresol.config.properties.ConfigurationManager;
import com.puresol.config.properties.PropertyDescription;
import com.puresol.utils.EnumUtilities;

public class CoCoMoServiceFactory implements ProjectEvaluatorFactory {

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyser) {
		CoCoMo cocomo = new CoCoMo(projectAnalyser);
		setConfiguration(cocomo);
		return cocomo;
	}

	private void setConfiguration(CoCoMo cocomo) {
		String complexity = "";
		String salary = "";
		String currency = "";
		for (PropertyDescription<?> description : CoCoMo.CONFIGURATION_PROPERTIES) {
			if (description.getClass().equals(ProjectComplexity.class)) {
				complexity = ConfigurationManager.getProperty(
						CoCoMo.class.getSimpleName(), description);
			} else if (description.getClass().equals(SalaryCurrency.class)) {
				currency = ConfigurationManager.getProperty(
						CoCoMo.class.getSimpleName(), description);
			} else if (description.getClass().equals(YearlyDeveloperSalary.class)) {
				salary = ConfigurationManager.getProperty(
						CoCoMo.class.getSimpleName(), description);
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
