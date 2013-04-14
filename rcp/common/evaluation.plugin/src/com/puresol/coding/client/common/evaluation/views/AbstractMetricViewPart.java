package com.puresol.coding.client.common.evaluation.views;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.coding.client.common.ui.actions.PartSettingsCapability;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.actions.Reproducable;
import com.puresol.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public abstract class AbstractMetricViewPart extends ViewPart implements
		Refreshable, Reproducable, ISelectionListener, PartSettingsCapability,
		EvaluationsTarget {

	private ISelectionService selectionService;
	private FileAnalysisSelection analysisSelection;

	public AbstractMetricViewPart() {
		super();
	}

	@Override
	public void dispose() {
		selectionService.removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {
		IWorkbenchPartSite site = getSite();
		IWorkbenchWindow workbenchWindow = site.getWorkbenchWindow();
		selectionService = workbenchWindow.getSelectionService();
		selectionService.addSelectionListener(this);
	}

	@Override
	public final void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof FileAnalysisSelection) {
			analysisSelection = (FileAnalysisSelection) selection;
			updateEvaluation();
		}
	}

	protected abstract void updateEvaluation();

	protected final FileAnalysisSelection getAnalysisSelection() {
		return analysisSelection;
	}

	protected final double findSuitableValue(HashIdFileTree path,
			MetricResults results, Parameter<?> parameter) {
		Map<String, Value<?>> valueMap = findSuitableValueMap(path, results,
				parameter);
		return convertToDouble(path, valueMap, parameter);
	}

	protected final Object findSuitableSecondaryValue(HashIdFileTree path,
			MetricResults results, Parameter<?> parameter) {
		Map<String, Value<?>> valueMap = findSuitableValueMap(path, results,
				parameter);
		Value<?> value = valueMap.get(parameter.getName());
		return value.getValue();
	}

	private Map<String, Value<?>> findSuitableValueMap(HashIdFileTree path,
			MetricResults results, Parameter<?> parameter) {
		Map<String, Value<?>> valueMap = null;
		List<Map<String, Value<?>>> values = results.getValues();
		if (path.isFile()) {
			if (values.size() == 1) {
				valueMap = values.get(0);
			} else {
				String codeRangeTypeParameterName = CodeRangeTypeParameter
						.getInstance().getName();
				for (Map<String, Value<?>> value : values) {
					if (value.get(codeRangeTypeParameterName).getValue()
							.equals(CodeRangeType.FILE)) {
						valueMap = value;
						break;
					}
				}
				if (valueMap == null) {
					throw new RuntimeException("File '"
							+ path.getPathFile(false)
							+ "' contains no FILE result for evaluator '"
							+ parameter.getName()
							+ "' and contains multiple values!");
				}
			}
		} else {
			if (values.size() != 1) {
				throw new RuntimeException("Directory '"
						+ path.getPathFile(false)
						+ "' contains more than one result for evaluator '"
						+ parameter.getName() + "'!");
			}
			valueMap = values.get(0);
		}
		return valueMap;
	}

	private double convertToDouble(HashIdFileTree path,
			Map<String, Value<?>> valueMap, Parameter<?> parameter) {
		double sum = 0.0;
		Value<?> value = valueMap.get(parameter.getName());
		if ((value != null)
				&& (Number.class.isAssignableFrom(parameter.getType()))) {
			Number number = (Number) value.getValue();
			sum = number.doubleValue();
		} else {
			throw new RuntimeException("Value '" + value
					+ "' is not a number for '" + path.getPathFile(false)
					+ "'!");
		}
		return sum;
	}

}
