package com.puresol.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
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

import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.branding.Printable;
import com.puresol.purifinity.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.purifinity.client.common.ui.actions.Exportable;
import com.puresol.purifinity.client.common.ui.actions.PartSettingsCapability;
import com.puresol.purifinity.client.common.ui.actions.Refreshable;
import com.puresol.purifinity.client.common.ui.actions.Reproducable;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.purifinity.coding.evaluation.api.MetricDirectoryResults;
import com.puresol.purifinity.coding.evaluation.api.MetricFileResults;
import com.puresol.purifinity.utils.math.Parameter;
import com.puresol.purifinity.utils.math.Value;

public abstract class AbstractMetricViewPart extends ViewPart implements
		Refreshable, Reproducable, ISelectionListener, PartSettingsCapability,
		EvaluationsTarget, Exportable, Printable {

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

	protected final Double findSuitableValue(HashIdFileTree path,
			MetricFileResults results, Parameter<?> parameter,
			CodeRangeType codeRangeType) {
		try {
			List<Map<String, Value<?>>> valueMaps = findSuitableValueMaps(path,
					results, parameter, codeRangeType);
			if ((valueMaps == null) || (valueMaps.size() != 1)) {
				return null;
			}
			return convertToDouble(valueMaps.get(0), parameter);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Could not find a suitable value for '"
					+ path.getPathFile(false).toString() + "'.", e);
		}
	}

	protected final Object findSuitableSecondaryValue(HashIdFileTree path,
			MetricFileResults results, Parameter<?> parameter,
			CodeRangeType codeRangeType) {
		List<Map<String, Value<?>>> valueMaps = findSuitableValueMaps(path,
				results, parameter, codeRangeType);
		if ((valueMaps == null) || (valueMaps.size() != 1)) {
			return null;
		}
		Value<?> value = valueMaps.get(0).get(parameter.getName());
		return value.getValue();
	}

	protected List<Map<String, Value<?>>> findSuitableValueMaps(
			HashIdFileTree path, MetricFileResults results,
			Parameter<?> parameter, CodeRangeType codeRangeType) {
		List<Map<String, Value<?>>> valueMap = new ArrayList<Map<String, Value<?>>>();
		List<Map<String, Value<?>>> values = results.getValues();
		if (path.isFile()) {
			if (values.size() == 1) {
				valueMap.add(values.get(0));
			} else {
				String codeRangeTypeParameterName = CodeRangeTypeParameter
						.getInstance().getName();
				for (Map<String, Value<?>> value : values) {
					Object codeRangeTypeValue = value.get(
							codeRangeTypeParameterName).getValue();
					if (codeRangeTypeValue.equals(codeRangeType)) {
						valueMap.add(value);
					}
				}
			}
		} else {
			if (values.size() != 1) {
				throw new RuntimeException("Directory '"
						+ path.getPathFile(false)
						+ "' contains more than one result for evaluator '"
						+ parameter.getName() + "'!");
			}
			valueMap.add(values.get(0));
		}
		return valueMap;
	}

	protected final double findSuitableValue(MetricDirectoryResults results,
			Parameter<?> parameter) {
		try {
			Map<String, Value<?>> valueMap = results.getValues();
			return convertToDouble(valueMap, parameter);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Could not find a suitable value.", e);
		}
	}

	protected final Object findSuitableSecondaryValue(HashIdFileTree path,
			MetricDirectoryResults results, Parameter<?> parameter) {
		Map<String, Value<?>> valueMap = results.getValues();
		Value<?> value = valueMap.get(parameter.getName());
		return value.getValue();
	}

	/**
	 * This method converts a valueMap into a Double value.
	 * 
	 * @param valueMap
	 * @param parameter
	 * @return
	 */
	protected double convertToDouble(Map<String, Value<?>> valueMap,
			Parameter<?> parameter) throws IllegalArgumentException {
		Value<?> value = valueMap.get(parameter.getName());
		if ((value != null) && (parameter.isNumeric())) {
			double sum = 0.0;
			Number number = (Number) value.getValue();
			sum = number.doubleValue();
			return sum;
		} else {
			throw new IllegalArgumentException("Value '" + value
					+ "' (parameter=" + parameter + ") is not a number!");
		}
	}
}
