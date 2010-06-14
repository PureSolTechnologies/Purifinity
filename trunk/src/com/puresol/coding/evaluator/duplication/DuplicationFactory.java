package com.puresol.coding.evaluator.duplication;

import java.util.List;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.EvaluatorFactory;
import com.puresol.coding.evaluator.NotSupportedException;
import com.puresol.utils.Property;

public class DuplicationFactory implements EvaluatorFactory {

	@Override
	public Evaluator create(CodeRange codeRange) throws NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public Evaluator create(Analyser analyser) throws NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public Evaluator create(ProjectAnalyser projectAnalyser)
			throws NotSupportedException {
		return new DuplicationScanner(projectAnalyser);
	}

	@Override
	public String getEvaluatorDescription() {
		return DuplicationScanner.DESCRIPTION;
	}

	@Override
	public String getEvaluatorName() {
		return DuplicationScanner.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return DuplicationScanner.SUPPORTED_PROPERTIES;
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
