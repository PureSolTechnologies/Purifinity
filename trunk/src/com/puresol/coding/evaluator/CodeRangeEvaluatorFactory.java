package com.puresol.coding.evaluator;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProgrammingLanguage;

public interface CodeRangeEvaluatorFactory extends EvaluatorFactory {

	public CodeRangeEvaluator create(ProgrammingLanguage language, CodeRange ast);

}
