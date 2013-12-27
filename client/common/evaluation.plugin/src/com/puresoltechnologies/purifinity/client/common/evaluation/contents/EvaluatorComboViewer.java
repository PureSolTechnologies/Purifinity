package com.puresoltechnologies.purifinity.client.common.evaluation.contents;

import org.apache.commons.io.IOUtils;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Combo;

import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.Evaluators;

public class EvaluatorComboViewer extends ComboViewer {

	private final Evaluators evaluators;

	public EvaluatorComboViewer(Combo list) {
		super(list);
		setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				EvaluatorFactory evaluator1 = (EvaluatorFactory) e1;
				EvaluatorFactory evaluator2 = (EvaluatorFactory) e2;
				return evaluator1.getName().compareTo(evaluator2.getName());
			}
		});
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

	public EvaluatorFactory getSelectedEvaluator() {
		IStructuredSelection selection = (IStructuredSelection) getSelection();
		return (EvaluatorFactory) selection.getFirstElement();
	}

	public int getNumber() {
		return evaluators.getAll().size();
	}
}