package com.puresoltechnologies.purifinity.client.common.evaluation.contents;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.widgets.Combo;

import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;
import com.puresoltechnologies.purifinity.client.common.server.Evaluators;

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
		List<EvaluatorFactory> evaluatorList = evaluators.getAll();
		Collections.sort(evaluatorList, new Comparator<EvaluatorFactory>() {
			@Override
			public int compare(EvaluatorFactory o1, EvaluatorFactory o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		setInput(evaluatorList);
	}

	public void dispose() {
		evaluators.close();
	}

	public EvaluatorFactory getSelectedEvaluator() {
		IStructuredSelection selection = (IStructuredSelection) getSelection();
		return (EvaluatorFactory) selection.getFirstElement();
	}

	public void setSelection(EvaluatorFactory evaluatorSelection) {
		if (evaluatorSelection != null) {
			setSelection(new StructuredSelection(evaluatorSelection));
		}
	}

	public int getNumber() {
		return evaluators.getAll().size();
	}
}
