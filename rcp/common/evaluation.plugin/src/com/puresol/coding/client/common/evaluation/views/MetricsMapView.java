package com.puresol.coding.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.puresol.coding.analysis.api.CodeRangeType;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.coding.client.common.evaluation.MetricsMapViewSettingsDialog;
import com.puresol.coding.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.coding.client.common.ui.actions.PartSettingsCapability;
import com.puresol.coding.client.common.ui.actions.RefreshAction;
import com.puresol.coding.client.common.ui.actions.Refreshable;
import com.puresol.coding.client.common.ui.actions.Reproducable;
import com.puresol.coding.client.common.ui.actions.ShowSettingsAction;
import com.puresol.coding.client.common.ui.actions.ViewReproductionAction;
import com.puresol.coding.client.common.ui.components.AreaMapComponent;
import com.puresol.coding.client.common.ui.components.AreaMapData;
import com.puresol.coding.evaluation.api.CodeRangeTypeParameter;
import com.puresol.coding.evaluation.api.EvaluatorFactory;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluation.api.EvaluatorStoreFactory;
import com.puresol.coding.evaluation.api.Evaluators;
import com.puresol.coding.evaluation.api.MetricResults;
import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

public class MetricsMapView extends ViewPart implements Refreshable,
		Reproducable, ISelectionListener, EvaluationsTarget,
		PartSettingsCapability {

	private ISelectionService selectionService;
	private FileAnalysisSelection analysisSelection;

	private Label label;

	private Composite container;
	private AreaMapComponent areaMap;

	private MetricsMapViewSettingsDialog settingsDialog;

	private EvaluatorFactory mapMetricSelection = null;
	private Parameter<?> mapValueSelection = null;
	private EvaluatorFactory colorMetricSelection = null;
	private Parameter<?> colorValueSelection = null;

	public MetricsMapView() {
		super();
	}

	@Override
	public void dispose() {
		selectionService.removeSelectionListener(this);
		super.dispose();
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		container = new Composite(parent, SWT.BORDER);
		container.setLayout(new FormLayout());

		label = new Label(container, SWT.BORDER);
		{
			FormData fd_label = new FormData();
			fd_label.left = new FormAttachment(0, 10);
			fd_label.right = new FormAttachment(100, -10);
			fd_label.top = new FormAttachment(0, 10);
			label.setLayoutData(fd_label);
		}

		areaMap = new AreaMapComponent(container, SWT.NONE);
		{
			FormData fd_areaMap = new FormData();
			fd_areaMap.left = new FormAttachment(label, 0, SWT.LEFT);
			fd_areaMap.right = new FormAttachment(label, 0, SWT.RIGHT);
			fd_areaMap.top = new FormAttachment(label, 10);
			fd_areaMap.bottom = new FormAttachment(100, -10);
			areaMap.setLayoutData(fd_areaMap);
		}

		IWorkbenchPartSite site = getSite();
		IWorkbenchWindow workbenchWindow = site.getWorkbenchWindow();
		selectionService = workbenchWindow.getSelectionService();
		selectionService.addSelectionListener(this);

		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(new ShowSettingsAction(this));
		toolbarManager.add(new ViewReproductionAction(this));
		toolbarManager.add(new RefreshAction(this));
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
		menuManager.add(new RefreshAction(this));
	}

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site, memento);
		if (memento == null) {
			return;
		}
		// touch old classes to get the plugins activated... :-(
		String mapMeticClass = memento.getString("map.metric.class");
		if (mapMeticClass != null) {
			try {
				Class.forName(mapMeticClass);
			} catch (ClassNotFoundException e) {
			}
		}
		String colorMeticClass = memento.getString("color.metric.class");
		if (colorMeticClass != null) {
			try {
				Class.forName(colorMeticClass);
			} catch (ClassNotFoundException e) {
			}
		}

		List<EvaluatorFactory> allMetrics = Evaluators.createInstance()
				.getAllMetrics();
		String mapMetricSelectionName = memento.getString("map.metric");
		String mapValueSelectionName = memento.getString("map.value");
		for (EvaluatorFactory metric : allMetrics) {
			if (metric.getName().equals(mapMetricSelectionName)) {
				mapMetricSelection = metric;
				if (mapValueSelectionName != null) {
					for (Parameter<?> parameter : mapMetricSelection
							.getParameters()) {
						if (parameter.getName().equals(mapValueSelectionName)) {
							mapValueSelection = parameter;
							break;
						}
					}
				}
				break;
			}
		}
		String colorMetricSelectionName = memento.getString("color.metric");
		String colorValueSelectionName = memento.getString("color.value");
		for (EvaluatorFactory metric : allMetrics) {
			if (metric.getName().equals(colorMetricSelectionName)) {
				colorMetricSelection = metric;
				if (colorValueSelectionName != null) {
					for (Parameter<?> parameter : colorMetricSelection
							.getParameters()) {
						if (parameter.getName().equals(colorValueSelectionName)) {
							colorValueSelection = parameter;
							break;
						}
					}
				}
				break;
			}
		}
	}

	@Override
	public void saveState(IMemento memento) {
		memento.putString("map.metric.class", mapMetricSelection.getClass()
				.getName());
		memento.putString("map.metric", mapMetricSelection.getName());
		memento.putString("map.value", mapValueSelection.getName());
		memento.putString("color.metric.class", colorMetricSelection.getClass()
				.getName());
		memento.putString("color.metric", colorMetricSelection.getName());
		memento.putString("color.value", colorValueSelection.getName());

		super.saveState(memento);
	}

	@Override
	public void setFocus() {
		areaMap.setFocus();
	}

	@Override
	public void refresh() {
		if (settingsDialog != null) {
			settingsDialog.refresh();
		}
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof FileAnalysisSelection) {
			analysisSelection = (FileAnalysisSelection) selection;
			updateEvaluation();
		}
	}

	private void updateEvaluation() {
		if ((analysisSelection != null) && (mapMetricSelection != null)
				&& (mapValueSelection != null)) {
			HashIdFileTree path = analysisSelection.getHashIdFile();
			if (path.isFile()) {
				path = path.getParent();
			}
			showEvaluation(path);
		}
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		label.setText(path.getPathFile(false).getPath());
		EvaluatorStore mapStore = EvaluatorStoreFactory.getFactory()
				.createInstance(mapMetricSelection.getEvaluatorClass());
		EvaluatorStore colorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(colorMetricSelection.getEvaluatorClass());
		AreaMapData data = calculateAreaData(mapStore, colorStore, path);
		areaMap.setData(data, mapValueSelection.getUnit());
	}

	/**
	 * This method calculates the map data for the {@link AreaMapComponent}.
	 * 
	 * This method is called recursively.
	 * 
	 * @param mapStore
	 *            is the {@link EvaluatorStore} to be used to load metric
	 *            results.
	 * @param path
	 *            is the path to be calculated.
	 * @return
	 */
	private AreaMapData calculateAreaData(EvaluatorStore mapStore,
			EvaluatorStore colorStore, HashIdFileTree path) {
		List<AreaMapData> childAreas = calculateChildAreaMaps(mapStore,
				colorStore, path);
		MetricResults mapResults;
		MetricResults colorResults;
		if (path.isFile()) {
			mapResults = mapStore.readFileResults(path.getHashId());
			colorResults = colorStore.readFileResults(path.getHashId());
		} else {
			mapResults = mapStore.readDirectoryResults(path.getHashId());
			colorResults = colorStore.readDirectoryResults(path.getHashId());
		}
		if ((mapResults == null) || (mapResults.getValues().size() == 0)) {
			return processAreaWithoutOwnValues(path,
					childAreas.toArray(new AreaMapData[childAreas.size()]));
		}
		double sum = findSuitableValue(path, mapResults, mapValueSelection);
		Object secondaryValue = null;
		if ((colorResults != null) && (colorResults.getValues().size() > 0)) {
			secondaryValue = findSuitableSecondaryValue(path, colorResults,
					colorValueSelection);
		}
		return new AreaMapData(path.getPathFile(false).toString(), sum,
				secondaryValue, childAreas.toArray(new AreaMapData[childAreas
						.size()]));
	}

	private double findSuitableValue(HashIdFileTree path,
			MetricResults results, Parameter<?> parameter) {
		Map<String, Value<?>> valueMap = findSuitableValueMap(path, results);
		return convertToDouble(path, valueMap, parameter);
	}

	private Object findSuitableSecondaryValue(HashIdFileTree path,
			MetricResults results, Parameter<?> parameter) {
		Map<String, Value<?>> valueMap = findSuitableValueMap(path, results);
		Value<?> value = valueMap.get(parameter.getName());
		return value.getValue();
	}

	private Map<String, Value<?>> findSuitableValueMap(HashIdFileTree path,
			MetricResults results) {
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
							+ mapMetricSelection.getName()
							+ "' and contains multiple values!");
				}
			}
		} else {
			if (values.size() != 1) {
				throw new RuntimeException("Directory '"
						+ path.getPathFile(false)
						+ "' contains more than one result for evaluator '"
						+ mapMetricSelection.getName() + "'!");
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

	/**
	 * This method calculates the values for all {@link HashIdFileTree}'s
	 * children.
	 * 
	 * @param mapStore
	 * @param colorStore
	 * @param path
	 * @return
	 */
	private List<AreaMapData> calculateChildAreaMaps(EvaluatorStore mapStore,
			EvaluatorStore colorStore, HashIdFileTree path) {
		List<HashIdFileTree> children = path.getChildren();
		List<AreaMapData> childAreas = new ArrayList<AreaMapData>();
		for (int i = 0; i < children.size(); i++) {
			AreaMapData areaData = calculateAreaData(mapStore, colorStore,
					children.get(i));
			if (areaData != null) {
				childAreas.add(areaData);
			}
		}
		Collections.sort(childAreas);
		return childAreas;
	}

	private AreaMapData processAreaWithoutOwnValues(HashIdFileTree path,
			AreaMapData[] childAreas) {
		double sum = 0.0;
		for (AreaMapData childArea : childAreas) {
			sum += childArea.getValue();
		}
		if (sum > 0.0) {
			return new AreaMapData(path.getPathFile(false).toString(), sum,
					childAreas);
		} else {
			return null;
		}
	}

	@Override
	public void showSettings() {
		if (settingsDialog == null) {
			settingsDialog = new MetricsMapViewSettingsDialog(this,
					mapMetricSelection, mapValueSelection,
					colorMetricSelection, colorValueSelection);
			settingsDialog.open();
		} else {
			settingsDialog.close();
			settingsDialog = null;
		}
	}

	@Override
	public void closeSettings() {
		settingsDialog = null;
	}

	@Override
	public void applySettings() {
		mapMetricSelection = settingsDialog.getMapMetric();
		mapValueSelection = settingsDialog.getMapValue();
		colorMetricSelection = settingsDialog.getColorMetric();
		colorValueSelection = settingsDialog.getColorValue();
		updateEvaluation();
	}
}
