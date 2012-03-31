package com.puresol.coding.client.content;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.puresol.coding.evaluator.ProjectEvaluatorFactory;

public class ProjectEvaluatorFactoryContentProvider implements
	IStructuredContentProvider {

    private final List<ProjectEvaluatorFactory> factories = new ArrayList<ProjectEvaluatorFactory>();

    @Override
    public void dispose() {
	// intentionally left blank
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	factories.clear();
	if (newInput != null) {
	    @SuppressWarnings("unchecked")
	    List<ProjectEvaluatorFactory> factoryList = (List<ProjectEvaluatorFactory>) newInput;
	    factories.addAll(factoryList);
	}
    }

    @Override
    public Object[] getElements(Object inputElement) {
	return factories.toArray();
    }
}
