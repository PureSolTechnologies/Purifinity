package com.puresol.coding.evaluator.duplication;

import java.util.List;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class DuplicationScannerFactory implements ProjectEvaluatorFactory {

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyser) {
		return new DuplicationScanner(projectAnalyser);
	}

	@Override
	public String getDescription() {
		return DuplicationScanner.DESCRIPTION;
	}

	@Override
	public String getName() {
		return DuplicationScanner.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return DuplicationScanner.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return DuplicationScanner.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
