package com.puresol.coding.evaluator.duplication;

import java.util.List;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class CopyAndPasteScannerFactory implements ProjectEvaluatorFactory {

	@Override
	public ProjectEvaluator create(ProjectAnalyzer projectAnalyser) {
		return new CopyAndPasteScanner(projectAnalyser);
	}

	@Override
	public String getDescription() {
		return CopyAndPasteScanner.DESCRIPTION;
	}

	@Override
	public String getName() {
		return CopyAndPasteScanner.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return CopyAndPasteScanner.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return CopyAndPasteScanner.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
