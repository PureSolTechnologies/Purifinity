package com.puresol.coding.lang.java.evaluator;

import java.util.List;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.EvaluatorFactory;
import com.puresol.coding.evaluator.NotSupportedException;
import com.puresol.utils.Property;

public class TranslatorImplementationFactory implements EvaluatorFactory {

	@Override
	public Evaluator create(CodeRange codeRange) throws NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public Evaluator create(Analyzer analyser) throws NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public Evaluator create(ProjectAnalyzer projectAnalyser)
			throws NotSupportedException {
		return new TranslatorImplementation(projectAnalyser);
	}

	@Override
	public String getEvaluatorDescription() {
		return TranslatorImplementation.DESCRIPTION;
	}

	@Override
	public String getEvaluatorName() {
		return TranslatorImplementation.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return TranslatorImplementation.SUPPORTED_PROPERTIES;
	}

	@Override
	public boolean isCodeRangeEvaluator() {
		return false;
	}

	@Override
	public boolean isFileEvaluator() {
		return false;
	}

	@Override
	public boolean isProjectEvaluator() {
		return true;
	}

}
