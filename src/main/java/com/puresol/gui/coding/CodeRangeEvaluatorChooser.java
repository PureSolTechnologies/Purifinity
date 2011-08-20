package com.puresol.gui.coding;

import java.util.Hashtable;
import java.util.List;

import com.puresol.coding.evaluator.CodeRangeEvaluatorFactory;
import com.puresol.coding.evaluator.CodeRangeEvaluatorManager;
import com.puresol.coding.evaluator.EvaluatorFactory;

public class CodeRangeEvaluatorChooser extends AbstractEvaluatorChooser {

	private static final long serialVersionUID = 9125928450820131366L;

	@Override
	protected synchronized void addEvaluators() {
		removeAll();
		List<CodeRangeEvaluatorFactory> evaluatorFactories = CodeRangeEvaluatorManager
				.getAll();
		Hashtable<Object, Object> values = new Hashtable<Object, Object>();
		for (EvaluatorFactory evaluatorFactory : evaluatorFactories) {
			values.put(evaluatorFactory.getName(), evaluatorFactory);
		}
		setListData(values);
	}
}
