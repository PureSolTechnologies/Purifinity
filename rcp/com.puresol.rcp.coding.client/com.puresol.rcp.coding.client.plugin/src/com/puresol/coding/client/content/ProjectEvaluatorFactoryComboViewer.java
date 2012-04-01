package com.puresol.coding.client.content;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Combo;

import com.puresol.coding.evaluator.EvaluatorUtils;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;

public class ProjectEvaluatorFactoryComboViewer extends ComboViewer {

    public ProjectEvaluatorFactoryComboViewer(Combo list) {
	super(list);
	setContentProvider(new ProjectEvaluatorFactoryContentProvider());
	setLabelProvider(new LabelProvider() {
	    @Override
	    public String getText(Object element) {
		ProjectEvaluatorFactory evaluator = (ProjectEvaluatorFactory) element;
		return evaluator.getName();
	    }
	});
	setInput(EvaluatorUtils.getProjectEvaluatorFactories());
    }
}
