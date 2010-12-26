package com.puresol.coding.metrics.cocomo;

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

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		// TODO Auto-generated method stub
		return null;
	}

}
