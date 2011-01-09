package com.puresol.gui.coding;

import java.util.Hashtable;
import java.util.List;

import javax.swingx.FreeList;

import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

import apps.CodeAnalysis;

import com.puresol.coding.evaluator.EvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorManager;
import com.puresol.osgi.OSGiFrameworkManager;

public class EvaluatorChooser extends FreeList {

	private static final long serialVersionUID = 8684347482453852261L;

	public EvaluatorChooser() {
		OSGiFrameworkManager.getInstance(CodeAnalysis.class.getName())
				.getContext().addServiceListener(new ServiceListener() {
					@Override
					public void serviceChanged(ServiceEvent event) {
						addEvaluators();
					}
				});
		addEvaluators();
	}

	private synchronized void addEvaluators() {
		removeAll();
		List<ProjectEvaluatorFactory> evaluatorFactories = ProjectEvaluatorManager
				.getAll();
		Hashtable<Object, Object> values = new Hashtable<Object, Object>();
		for (EvaluatorFactory evaluatorFactory : evaluatorFactories) {
			values.put(evaluatorFactory.getName(), evaluatorFactory);
		}
		setListData(values);
	}

}
