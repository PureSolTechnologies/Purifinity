package com.puresol.coding.client.common.evaluation.contents;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import com.puresol.utils.math.Parameter;

public class ParametersListViewer extends ListViewer implements
	IStructuredContentProvider {

    private Set<Parameter<?>> parameters = new HashSet<Parameter<?>>();

    public ParametersListViewer(Composite parent, int style) {
	super(parent, style);
	setContentProvider(this);
    }

    public ParametersListViewer(Composite parent) {
	super(parent);
	setContentProvider(this);
    }

    public ParametersListViewer(List list) {
	super(list);
	setContentProvider(this);
    }

    @Override
    public void dispose() {
	// intentionally left blank
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	@SuppressWarnings("unchecked")
	Set<Parameter<?>> input = (Set<Parameter<?>>) newInput;
	parameters = input;
    }

    @Override
    public Parameter<?>[] getElements(Object inputElement) {
	return parameters.toArray(new Parameter<?>[parameters.size()]);
    }

}
