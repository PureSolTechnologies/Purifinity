package com.puresol.coding.analysis.evaluator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;

public class UnsupportedByEvaluatorException extends Exception {

	private static final long serialVersionUID = -2440127803107554370L;

	public UnsupportedByEvaluatorException(CodeEvaluator codeEvaluator,
			CodeRange codeRange) {
		super("Code range " + codeRange.getTypeName() + " '"
				+ codeRange.getName() + "' is not supported by "
				+ codeEvaluator.getName() + "!");
	}

	public UnsupportedByEvaluatorException(ProjectEvaluator projectEvaluator,
			ProjectAnalyser project) {
		super("Project in directory " + project.getProjectDirectory().getPath()
				+ " ' is not supported by " + projectEvaluator.getName() + "!");
	}

}
