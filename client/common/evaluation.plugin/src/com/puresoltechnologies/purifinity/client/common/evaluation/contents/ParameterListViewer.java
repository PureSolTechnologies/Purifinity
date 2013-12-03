package com.puresoltechnologies.purifinity.client.common.evaluation.contents;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import com.puresoltechnologies.commons.math.Parameter;

public class ParameterListViewer extends ListViewer implements
		IStructuredContentProvider {

	private class ParameterListLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			Parameter<?> parameter = (Parameter<?>) element;
			return parameter.getName();
		}
	}

	private Set<Parameter<?>> parameters = new HashSet<Parameter<?>>();

	public ParameterListViewer(Composite parent, int style) {
		super(parent, style);
		setContentProvider(this);
		setLabelProvider(new ParameterListLabelProvider());
	}

	public ParameterListViewer(Composite parent) {
		super(parent);
		setContentProvider(this);
		setLabelProvider(new ParameterListLabelProvider());
	}

	public ParameterListViewer(List list) {
		super(list);
		setContentProvider(this);
		setLabelProvider(new ParameterListLabelProvider());
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
