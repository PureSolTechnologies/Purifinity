package com.puresoltechnologies.purifinity.client.common.evaluation.controls;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.ListenerList;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IMemento;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.client.common.analysis.contents.CodeRangeTypeComboViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.EvaluatorComboViewer;
import com.puresoltechnologies.purifinity.client.common.evaluation.contents.ParameterComboViewer;
import com.puresoltechnologies.purifinity.client.common.ui.SWTUtils;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.framework.evaluation.commons.impl.Evaluators;

/**
 * This components provides a selection for an evaluator and a parameters.
 * 
 * @author Rick-Rainer Ludwig
 */
public class MetricParameterSelectionComponent extends Composite implements
		SelectionListener {

	private static final String METRIC_CLASS_PROPERTY = "metric.class";
	private static final String METRIC_NAME_PROPERTY = "metric.name";
	private static final String PARAMETER_NAME_PROPERTY = "parameter.name";
	private static final String CODE_RANGE_TYPE_NAME_PROPERTY = "code.range.type";

	public static MetricParameterSelection init(IMemento memento) {
		EvaluatorFactory evaluatorSelection = null;
		Parameter<?> parameterSelection = null;
		CodeRangeType codeRangeTypeSelection = null;
		if (memento != null) {
			String mapMeticClass = memento.getString(METRIC_CLASS_PROPERTY);
			if (mapMeticClass != null) {
				// touch old classes to get the plugins activated... :-(
				try {
					Class.forName(mapMeticClass);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(
							"Could not load evaluator class '" + mapMeticClass
									+ "'.", e);
				}
			}
			String metricSelectionName = memento
					.getString(METRIC_NAME_PROPERTY);
			String parameterSelectionName = memento
					.getString(PARAMETER_NAME_PROPERTY);
			if ((metricSelectionName != null)
					&& (parameterSelectionName != null)) {
				List<EvaluatorFactory> allMetrics = Evaluators.createInstance()
						.getAllMetrics();
				for (EvaluatorFactory metric : allMetrics) {
					if (metric.getName().equals(metricSelectionName)) {
						evaluatorSelection = metric;
						if (parameterSelectionName != null) {
							for (Parameter<?> parameter : evaluatorSelection
									.getParameters()) {
								if (parameter.getName().equals(
										parameterSelectionName)) {
									parameterSelection = parameter;
									break;
								}
							}
						}
						break;
					}
				}
			}
			String codeRangeTypeName = memento
					.getString(CODE_RANGE_TYPE_NAME_PROPERTY);
			if (codeRangeTypeName != null) {
				codeRangeTypeSelection = CodeRangeType
						.valueOf(codeRangeTypeName);
			}
		}
		return new MetricParameterSelection(evaluatorSelection,
				parameterSelection, codeRangeTypeSelection);
	}

	public static void saveState(IMemento memento,
			MetricParameterSelection metricParameterSelection) {
		EvaluatorFactory evaluatorSelection = metricParameterSelection
				.getEvaluatorFactory();
		if (evaluatorSelection != null) {
			memento.putString(METRIC_CLASS_PROPERTY, evaluatorSelection
					.getClass().getName());
			memento.putString(METRIC_NAME_PROPERTY,
					evaluatorSelection.getName());
			Parameter<?> parameterSelection = metricParameterSelection
					.getParameter();
			if (parameterSelection != null) {
				memento.putString(PARAMETER_NAME_PROPERTY,
						parameterSelection.getName());
			}
		} else {
			memento.putString(METRIC_CLASS_PROPERTY, null);
			memento.putString(METRIC_NAME_PROPERTY, null);
			memento.putString(PARAMETER_NAME_PROPERTY, null);
		}
		CodeRangeType codeRangeTypeSelection = metricParameterSelection
				.getCodeRangeType();
		if (codeRangeTypeSelection != null) {
			memento.putString(CODE_RANGE_TYPE_NAME_PROPERTY,
					codeRangeTypeSelection.name());
		} else {
			memento.putString(CODE_RANGE_TYPE_NAME_PROPERTY, null);
		}
	}

	private final ListenerList listenerList = new ListenerList();

	private final LevelOfMeasurement levelOfMeasurement;

	private final Combo evaluatorCombo;
	private final EvaluatorComboViewer evaluatorComboViewer;
	private final Combo parameterCombo;
	private final ParameterComboViewer parameterComboViewer;
	private final Combo codeRangeTypeCombo;
	private final CodeRangeTypeComboViewer codeRangeTypeComboViewer;

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

		codeRangeTypeCombo = new Combo(group, SWT.READ_ONLY);
		FormData fdRangeCombo = new FormData();
		fdRangeCombo.left = new FormAttachment(0, SWTUtils.DEFAULT_MARGIN);
		fdRangeCombo.right = new FormAttachment(100, -SWTUtils.DEFAULT_MARGIN);
		fdRangeCombo.top = new FormAttachment(parameterCombo,
				SWTUtils.DEFAULT_MARGIN);
		codeRangeTypeCombo.setLayoutData(fdRangeCombo);
		codeRangeTypeCombo.addSelectionListener(this);

		evaluatorComboViewer = new EvaluatorComboViewer(evaluatorCombo);
		parameterComboViewer = new ParameterComboViewer(parameterCombo);
		codeRangeTypeComboViewer = new CodeRangeTypeComboViewer(
				codeRangeTypeCombo);
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
		} else if (e.getSource() == codeRangeTypeCombo) {
			codeSelectionChanged();
		}
	}

	private void evaluationSelectionChanged() {
		StructuredSelection selection = (StructuredSelection) evaluatorComboViewer
				.getSelection();
		EvaluatorFactory evaluatorFactory = (EvaluatorFactory) selection
				.getFirstElement();
		if (evaluatorFactory != null) {
			evaluatorCombo.setToolTipText(evaluatorFactory.getDescription());
			Set<Parameter<?>> parameters = new LinkedHashSet<>();
			for (Parameter<?> parameter : evaluatorFactory.getParameters()) {
				if (parameter.getLevelOfMeasurement().isAtLeast(
						levelOfMeasurement)) {
					parameters.add(parameter);
				}
			}
			parameterComboViewer.setInput(parameters);
		} else {
			evaluatorCombo.setToolTipText(null);
			parameterComboViewer.setInput(null);
		}
	}

	private void parameterSelectionChanged() {
		StructuredSelection selection = (StructuredSelection) parameterComboViewer
				.getSelection();
		Parameter<?> parameter = (Parameter<?>) selection.getFirstElement();
		if (parameter != null) {
			parameterCombo.setToolTipText(parameter.getDescription());
		} else {
			parameterCombo.setToolTipText(null);
		}
		codeSelectionChanged();
	}

	private void codeSelectionChanged() {
		if ((evaluatorComboViewer.getSelectedEvaluator() == null)
				|| (parameterComboViewer.getSelectedParameter() == null)
				|| (codeRangeTypeComboViewer.getSelectedCodeRangeType() == null)) {
			return;
		}
		Event typedEvent = new Event();
		typedEvent.widget = this;
		SelectionEvent selectionEvent = new SelectionEvent(typedEvent);
		for (Object listener : listenerList.getListeners()) {
			SelectionListener selectionListener = (SelectionListener) listener;
			selectionListener.widgetSelected(selectionEvent);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		evaluatorCombo.select(0);
		evaluationSelectionChanged();
		parameterCombo.select(0);
		parameterSelectionChanged();
		codeRangeTypeCombo.select(0);
		codeSelectionChanged();
	}

	public void addSelectionListener(SelectionListener selectionListener) {
		listenerList.add(selectionListener);
	}

	public void removeSelectionListener(SelectionListener selectionListener) {
		listenerList.remove(selectionListener);
	}

	public MetricParameterSelection getSelection() {
		return new MetricParameterSelection(
				evaluatorComboViewer.getSelectedEvaluator(),
				parameterComboViewer.getSelectedParameter(),
				codeRangeTypeComboViewer.getSelectedCodeRangeType());
	}

	public void setSelection(MetricParameterSelection metricParameterSelection) {
		EvaluatorFactory evaluatorFactory = metricParameterSelection
				.getEvaluatorFactory();
		if (evaluatorFactory != null) {
			evaluatorComboViewer.setSelection(evaluatorFactory);
			evaluationSelectionChanged();
			Parameter<?> parameter = metricParameterSelection.getParameter();
			parameterComboViewer.setSelection(parameter);
		} else {
			evaluatorComboViewer.setSelection((EvaluatorFactory) null);
			parameterComboViewer.setSelection((Parameter<?>) null);
		}
		CodeRangeType codeRangeType = metricParameterSelection
				.getCodeRangeType();
		codeRangeTypeComboViewer.setSelection(codeRangeType);
	}

}
