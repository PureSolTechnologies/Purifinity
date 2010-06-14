package com.puresol.coding.metrics.codedepth;

import java.util.List;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.EvaluatorFactory;
import com.puresol.coding.evaluator.NotSupportedException;
import com.puresol.utils.Property;

public class CodeDepthFactory implements EvaluatorFactory {

	@Override
	public Evaluator create(CodeRange codeRange) throws NotSupportedException {
		return new CodeDepth(codeRange);
	}

	@Override
	public Evaluator create(Analyser analyser) throws NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public Evaluator create(ProjectAnalyser projectAnalyser)
			throws NotSupportedException {
		throw new NotSupportedException();
	}

	@Override
	public String getEvaluatorDescription() {
		return CodeDepth.DESCRIPTION;
	}

	@Override
	public String getEvaluatorName() {
		return CodeDepth.NAME;
	}

	@Override
	public List<Property> getEvaluatorProperties() {
		return CodeDepth.SUPPORTED_PROPERTIES;
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
