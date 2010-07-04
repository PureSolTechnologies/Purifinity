package com.puresol.coding.lang.java.evaluator;

import java.util.List;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.evaluator.FileEvaluator;
import com.puresol.coding.evaluator.FileEvaluatorFactory;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.utils.Property;

public class TranslatorImplementationFactory implements FileEvaluatorFactory {

	@Override
	public FileEvaluator create(Analyzer analyser) {
		return new TranslatorImplementation(analyser);
	}

	@Override
	public String getDescription() {
		return TranslatorImplementation.DESCRIPTION;
	}

	@Override
	public String getName() {
		return TranslatorImplementation.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return TranslatorImplementation.SUPPORTED_PROPERTIES;
	}

	@Override
	public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
		return TranslatorImplementation.EVALUATED_QUALITY_CHARACTERISTICS;
	}

}
