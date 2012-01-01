package com.puresol.gui.coding;

import java.util.Hashtable;
import java.util.List;

import com.puresol.coding.evaluator.EvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorManager;

public class ProjectEvaluatorChooser extends AbstractEvaluatorChooser {

	private static final long serialVersionUID = 9125928450820131366L;

	@Override
	protected synchronized void addEvaluators() {
		removeAll();
		List<ProjectEvaluatorFactory> evaluatorFactories = ProjectEvaluatorManager
				.getAll();
		Hashtable<Object, Object> values = new Hashtable<Object, Object>();
		for (EvaluatorFactory evaluatorFactory : evaluatorFactories) {
			values.put(evaluatorFactory.getName(), evaluatorFactory);
		}
		setListData(values);
	}
}
