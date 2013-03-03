package com.puresol.coding.client.application.content;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Combo;

import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.Evaluators;

public class EvaluatorComboViewer extends ComboViewer {

    private final Evaluators evaluators;

    public EvaluatorComboViewer(Combo list) {
	super(list);
	setContentProvider(new EvaluatorsContentProvider());
	setLabelProvider(new LabelProvider() {
	    @Override
	    public String getText(Object element) {
		EvaluatorFactory evaluator = (EvaluatorFactory) element;
		return evaluator.getName();
	    }
	});
	evaluators = Evaluators.createInstance();
	setInput(evaluators.getAll());
    }

    public void dispose() {
	IOUtils.closeQuietly(evaluators);
    }
}
