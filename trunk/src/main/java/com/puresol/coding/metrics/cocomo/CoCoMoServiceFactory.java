package com.puresol.coding.metrics.cocomo;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class CoCoMoServiceFactory implements ProjectEvaluatorFactory {

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyser) {
		return new CoCoMo(projectAnalyser);
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

	@Override
	public List<Property> getEvaluatorProperties() {
		return CoCoMo.SUPPORTED_PROPERTIES;
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
