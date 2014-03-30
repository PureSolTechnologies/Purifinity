package com.puresoltechnologies.purifinity.client.common.evaluation.controls;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.EvaluatorComboViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.ParameterComboViewer;
import com.puresoltechnologies.purifinity.client.common.ui.SWTUtils;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;

/**
 * This components provides a selection for an evaluator and a parameters.
 * 
 * @author Rick-Rainer Ludwig
 */
public class MetricParameterSelectionComponent extends Composite implements
		SelectionListener {

	private final LevelOfMeasurement levelOfMeasurement;

	private final Combo evaluatorCombo;
	private final EvaluatorComboViewer evaluatorComboViewer;
	private final Combo parameterCombo;
	private final ParameterComboViewer parameterComboViewer;

	public MetricParameterSelectionComponent(Composite parent,
			LevelOfMeasurement levelOfMeasurement, String title) {
		super(parent, SWT.NONE);
		this.levelOfMeasurement = levelOfMeasurement;
		setLayout(new FillLayout());

		Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
		group.setText(title);

		FormLayout layout = new FormLayout();
		group.setLayout(layout);

		evaluatorCombo = new Combo(group, SWT.READ_ONLY);
		FormData fdEvaluatorCombo = new FormData();
		fdEvaluatorCombo.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdEvaluatorCombo.right = new FormAttachment(100,
				-SWTUtils.DEFAULT_MARGIN);
		fdEvaluatorCombo.top = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		evaluatorCombo.setLayoutData(fdEvaluatorCombo);
		evaluatorCombo.addSelectionListener(this);

		parameterCombo = new Combo(group, SWT.READ_ONLY);
		FormData fdParameterCombo = new FormData();
		fdParameterCombo.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdParameterCombo.right = new FormAttachment(100,
				-SWTUtils.DEFAULT_MARGIN);
		fdParameterCombo.top = new FormAttachment(evaluatorCombo,
				SWTUtils.DEFAULT_MARGIN);
		parameterCombo.setLayoutData(fdParameterCombo);
		parameterCombo.addSelectionListener(this);

		FormData fdDescription = new FormData();
		fdDescription.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdDescription.right = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);
		fdDescription.top = new FormAttachment(parameterCombo,
				SWTUtils.DEFAULT_MARGIN);
		fdDescription.bottom = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);

		evaluatorComboViewer = new EvaluatorComboViewer(evaluatorCombo);
		parameterComboViewer = new ParameterComboViewer(parameterCombo);

		evaluatorCombo.select(0);
		evaluationSelectionChanged();
		parameterCombo.select(0);
		parameterSelectionChanged();
	}

	@Override
	public void dispose() {
		parameterComboViewer.dispose();
		parameterCombo.dispose();
		evaluatorComboViewer.dispose();
		evaluatorCombo.dispose();
		super.dispose();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == evaluatorCombo) {
			evaluationSelectionChanged();
		} else if (e.getSource() == parameterCombo) {
			parameterSelectionChanged();
		}
	}

	private void evaluationSelectionChanged() {
		StructuredSelection selection = (StructuredSelection) evaluatorComboViewer
				.getSelection();
		EvaluatorFactory evaluatorFactory = (EvaluatorFactory) selection
				.getFirstElement();
		evaluatorCombo.setToolTipText(evaluatorFactory.getDescription());
		Set<Parameter<?>> parameters = new LinkedHashSet<>();
		for (Parameter<?> parameter : evaluatorFactory.getParameters()) {
			if (parameter.getLevelOfMeasurement().isAtLeast(levelOfMeasurement)) {
				parameters.add(parameter);
			}
		}
		parameterComboViewer.setInput(parameters);
		parameterCombo.select(0);
	}

	private void parameterSelectionChanged() {
		StructuredSelection selection = (StructuredSelection) parameterComboViewer
				.getSelection();
		Parameter<?> parameter = (Parameter<?>) selection.getFirstElement();
		parameterCombo.setToolTipText(parameter.getDescription());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		evaluatorCombo.select(0);
		evaluationSelectionChanged();
		parameterCombo.select(0);
		parameterSelectionChanged();
	}
}
