package com.puresoltechnologies.purifinity.client.common.evaluation.controls;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

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
	private final Text description;

	public MetricParameterSelectionComponent(
			LevelOfMeasurement levelOfMeasurement, Composite parent, int style) {
		super(parent, style);
		this.levelOfMeasurement = levelOfMeasurement;

		FormLayout layout = new FormLayout();
		setLayout(layout);

		evaluatorCombo = new Combo(this, SWT.READ_ONLY);
		FormData fdEvaluatorCombo = new FormData();
		fdEvaluatorCombo.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdEvaluatorCombo.right = new FormAttachment(100,
				-SWTUtils.DEFAULT_MARGIN);
		fdEvaluatorCombo.top = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		evaluatorCombo.setLayoutData(fdEvaluatorCombo);
		evaluatorCombo.addSelectionListener(this);

		parameterCombo = new Combo(this, SWT.READ_ONLY);
		FormData fdParameterCombo = new FormData();
		fdParameterCombo.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdParameterCombo.right = new FormAttachment(100,
				-SWTUtils.DEFAULT_MARGIN);
		fdParameterCombo.top = new FormAttachment(evaluatorCombo,
				SWTUtils.DEFAULT_MARGIN);
		parameterCombo.setLayoutData(fdParameterCombo);
		parameterCombo.addSelectionListener(this);

		description = new Text(this, SWT.READ_ONLY | SWT.MULTI
				| SWT.SCROLL_LINE | SWT.SCROLL_PAGE | SWT.SCROLLBAR_OVERLAY
				| SWT.V_SCROLL | SWT.WRAP);
		FormData fdDescription = new FormData();
		fdDescription.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdDescription.right = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);
		fdDescription.top = new FormAttachment(parameterCombo,
				SWTUtils.DEFAULT_MARGIN);
		fdDescription.bottom = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);
		description.setLayoutData(fdDescription);

		evaluatorComboViewer = new EvaluatorComboViewer(evaluatorCombo);
		evaluatorCombo.select(0);

		parameterComboViewer = new ParameterComboViewer(parameterCombo);
		parameterCombo.select(0);
	}

	@Override
	public void dispose() {
		description.dispose();
		parameterComboViewer.dispose();
		parameterCombo.dispose();
		evaluatorComboViewer.dispose();
		evaluatorCombo.dispose();
		super.dispose();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		if (e.getSource() == evaluatorCombo) {
			StructuredSelection selection = (StructuredSelection) evaluatorComboViewer
					.getSelection();
			EvaluatorFactory evaluatorFactory = (EvaluatorFactory) selection
					.getFirstElement();
			// parameterComboViewer.set
			parameterComboViewer.setInput(evaluatorFactory.getParameters());
			parameterCombo.select(0);
		} else if (e.getSource() == parameterCombo) {
			StructuredSelection selection = (StructuredSelection) parameterComboViewer
					.getSelection();
			Parameter<?> parameter = (Parameter<?>) selection.getFirstElement();
			description.setText(parameter.getDescription());
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		evaluatorCombo.select(0);
		parameterCombo.select(0);
	}
}
