package com.puresol.purifinity.client.common.evaluation.contents;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Combo;

import com.puresol.purifinity.utils.math.Parameter;

public class ParameterComboViewer extends ComboViewer implements
		IStructuredContentProvider {

	private final List<Parameter<?>> parameters = new ArrayList<Parameter<?>>();

	public ParameterComboViewer(Combo combo) {
		super(combo);
		setContentProvider(this);
		setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				Parameter<?> parameter = (Parameter<?>) element;
				StringBuilder builder = new StringBuilder(parameter.getName());
				String unit = parameter.getUnit();
				if ((unit != null) && (!unit.isEmpty())) {
					builder.append("[").append(parameter.getUnit()).append("]");
				}
				String description = parameter.getDescription();
				if ((description != null) && (!description.isEmpty())) {
					builder.append("(").append(parameter.getDescription())
							.append(")");
				}
				return builder.toString();
			}
		});
	}

	@Override
	public void dispose() {
		parameters.clear();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		parameters.clear();
		if (newInput != null) {
			@SuppressWarnings("unchecked")
			Set<Parameter<?>> factoryList = (Set<Parameter<?>>) newInput;
			parameters.addAll(factoryList);
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return parameters.toArray();
	}

}
