package com.puresoltechnologies.purifinity.client.common.evaluation.contents;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Combo;

import com.puresoltechnologies.purifinity.coding.evaluation.api.EvaluatorFactory;

public class MetricComboViewer extends ComboViewer {

	public MetricComboViewer(Combo list) {
		super(list);
		setContentProvider(new EvaluatorsContentProvider());
		setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				EvaluatorFactory evaluator = (EvaluatorFactory) element;
				return evaluator.getName();
			}
		});
	}
}
