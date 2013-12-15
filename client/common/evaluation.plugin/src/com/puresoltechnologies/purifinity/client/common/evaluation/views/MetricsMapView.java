package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.api.HashIdFileTree;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.branding.Printable;
import com.puresoltechnologies.purifinity.client.common.chart.AreaMapComponent;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.AreaMapData;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.AreaMapRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.MetricsMapViewSettingsDialog;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.EvaluatorFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.coding.evaluation.impl.Evaluators;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.MetricDirectoryResults;
import com.puresoltechnologies.purifinity.evaluation.api.MetricFileResults;

public class MetricsMapView extends AbstractMetricViewPart implements Printable {

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

		initializeToolBar();
		initializeMenu();
		super.createPartControl(parent);
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getToolBarManager();
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
	protected void updateEvaluation() {
		AnalysisSelection analysisSelection = getAnalysisSelection();
		if ((analysisSelection != null) && (mapMetricSelection != null)
				&& (mapValueSelection != null)) {
			HashIdFileTree path = analysisSelection.getFileTreeNode();
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
		setColorProvider();
		areaMap.setData(data, mapValueSelection.getUnit());
	}

	private void setColorProvider() {
		try {
			BundleContext bundleContext = Activator.getDefault().getBundle()
					.getBundleContext();
			String parameterName = colorValueSelection.getName();
			String filter = "(parameterName=" + parameterName + ")";
			Collection<ServiceReference<ColorProvider>> serviceReferences = bundleContext
					.getServiceReferences(ColorProvider.class, filter);
			if (!serviceReferences.isEmpty()) {
				ServiceReference<ColorProvider> serviceReference = serviceReferences
						.iterator().next();
				try {
					ColorProvider provider = bundleContext
							.getService(serviceReference);
					areaMap.setColorProvider(provider);
				} finally {
					bundleContext.ungetService(serviceReference);
				}
			} else {
				areaMap.setColorProvider(null);
			}
		} catch (InvalidSyntaxException e) {
			throw new RuntimeException(e);
		}
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
		Object secondaryValue = null;
		Double sum;
		if (path.isFile()) {
			MetricFileResults mapResults = mapStore.readFileResults(path
					.getHashId());
			MetricFileResults colorResults = colorStore.readFileResults(path
					.getHashId());
			if ((mapResults == null) || (mapResults.getValues().size() == 0)) {
				return processAreaWithoutOwnValues(path,
						childAreas.toArray(new AreaMapData[childAreas.size()]));
			}
			sum = findSuitableValue(path, mapResults, mapValueSelection,
					CodeRangeType.FILE);
			if ((colorResults != null) && (colorResults.getValues().size() > 0)) {
				secondaryValue = findSuitableSecondaryValue(path, colorResults,
						colorValueSelection, CodeRangeType.FILE);
			}
		} else {
			MetricDirectoryResults mapResults = mapStore
					.readDirectoryResults(path.getHashId());
			MetricDirectoryResults colorResults = colorStore
					.readDirectoryResults(path.getHashId());
			if ((mapResults == null) || (mapResults.getValues().size() == 0)) {
				return processAreaWithoutOwnValues(path,
						childAreas.toArray(new AreaMapData[childAreas.size()]));
			}
			sum = findSuitableValue(mapResults, mapValueSelection);
			if ((colorResults != null) && (colorResults.getValues().size() > 0)) {
				secondaryValue = findSuitableSecondaryValue(path, colorResults,
						colorValueSelection);
			}
		}
		if (sum == null) {
			sum = 0.0;
		}
		return new AreaMapData(path.getPathFile(false).toString(), sum,
				secondaryValue, childAreas.toArray(new AreaMapData[childAreas
						.size()]));
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

	@Override
	public void export() {
		MessageDialog.openInformation(getSite().getShell(), "Not implemented",
				"This functionality is not implemented, yet!");
	}

	@Override
	public void print(Printer printer, String printJobName) {
		printer.startJob(printJobName);
		try {
			GC gc = new GC(printer);
			try {
				printer.startPage();
				Rectangle clientArea = printer.getClientArea();
				AreaMapData data = areaMap.getData();
				AreaMapRenderer renderer = new AreaMapRenderer();
				renderer.setColorProvider(areaMap.getColorProvider());
				renderer.render(gc, data, clientArea.x, clientArea.y,
						clientArea.x + clientArea.width - 1, clientArea.y
								+ clientArea.height - 1);
				printer.endPage();
			} finally {
				gc.dispose();
			}
		} finally {
			printer.endJob();
		}
	}
}
