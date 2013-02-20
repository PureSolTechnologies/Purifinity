package com.puresol.coding.client.application.content;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.coding.evaluation.api.Evaluator;

public class EvaluatorsContentProvider implements IStructuredContentProvider {

    private final List<Evaluator> factories = new ArrayList<Evaluator>();

    @Override
    public void dispose() {
	// intentionally left blank
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	factories.clear();
	if (newInput != null) {
	    @SuppressWarnings("unchecked")
	    List<Evaluator> factoryList = (List<Evaluator>) newInput;
	    factories.addAll(factoryList);
	}
    }

    @Override
    public Object[] getElements(Object inputElement) {
	return factories.toArray();
    }
}
