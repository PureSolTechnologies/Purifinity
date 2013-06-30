package com.puresol.purifinity.client.common.evaluation.contents;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Combo;

import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluators;

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
