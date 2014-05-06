package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
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
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.client.common.analysis.AnalysisSelections;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.branding.Printable;
import com.puresoltechnologies.purifinity.client.common.chart.AreaMapComponent;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.AreaMapData;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.AreaMapRenderer;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.MetricsMapViewSettingsDialog;
import com.puresoltechnologies.purifinity.client.common.server.EvaluatorFactory;
import com.puresoltechnologies.purifinity.client.common.server.Evaluators;
import com.puresoltechnologies.purifinity.client.common.ui.actions.RefreshAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresoltechnologies.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.server.client.socket.MetricsMapDataProviderClient;
import com.puresoltechnologies.purifinity.server.domain.MetricsMapData;

public class MetricsMapView extends AbstractMetricViewPart implements Printable {

	private EvaluatorFactory mapMetricSelection = null;
	private EvaluatorFactory oldMapMetricSelection = null;
	private Parameter<?> mapParameterSelection = null;
	private Parameter<?> oldMapParameterSelection = null;
	private EvaluatorFactory colorMetricSelection = null;
	private EvaluatorFactory oldColorMetricSelection = null;
	private Parameter<?> colorParameterSelection = null;
	private Parameter<?> oldColorParameterSelection = null;

	private Label label;

	private Composite container;
	private AreaMapComponent areaMap;

	private MetricsMapViewSettingsDialog settingsDialog;

	private MetricsMapData mapValues = new MetricsMapData();

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

		setAnalysisSelection(AnalysisSelections.getInstance()
				.getAnalysisSelection());
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
							mapParameterSelection = parameter;
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
							colorParameterSelection = parameter;
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
		if (mapMetricSelection != null) {
			memento.putString("map.metric.class", mapMetricSelection.getClass()
					.getName());
			memento.putString("map.metric", mapMetricSelection.getName());
		}
		if (mapParameterSelection != null) {
			memento.putString("map.value", mapParameterSelection.getName());
		}
		if (colorMetricSelection != null) {
			memento.putString("color.metric.class", colorMetricSelection
					.getClass().getName());
			memento.putString("color.metric", colorMetricSelection.getName());
		}
		if (colorParameterSelection != null) {
			memento.putString("color.value", colorParameterSelection.getName());
		}
		super.saveState(memento);
	}

	@Override
	public void refresh() {
		if (settingsDialog != null) {
			settingsDialog.refresh();
		}
	}

	@Override
	protected void clear() {
		showEvaluation(null);
	}

	@Override
	protected void updateView() {
		oldMapMetricSelection = mapMetricSelection;
		oldMapParameterSelection = mapParameterSelection;
		oldColorMetricSelection = colorMetricSelection;
		oldColorParameterSelection = colorParameterSelection;
		loadData();
	}

	@Override
	protected boolean hasFullViewSettings() {
		return (mapMetricSelection != null) && (mapParameterSelection != null)
				&& (colorMetricSelection != null)
				&& (colorParameterSelection != null);
	}

	@Override
	protected boolean hasChangedViewSettings() {
		if ((oldMapMetricSelection == null)
				|| (!mapMetricSelection.getClass().equals(
						oldMapMetricSelection.getClass()))) {
			return true;
		}
		if (!oldMapParameterSelection.equals(mapParameterSelection)) {
			return true;
		}
		if ((oldColorMetricSelection == null)
				|| (!colorMetricSelection.getClass().equals(
						oldColorMetricSelection.getClass()))) {
			return true;
		}
		if (!oldColorParameterSelection.equals(colorParameterSelection)) {
			return true;
		}
		return false;
	}

	private void loadData() {
		Job job = new Job("Metrics Map") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Load data", 5);
				try (MetricsMapDataProviderClient client = new MetricsMapDataProviderClient()) {
					monitor.worked(1);
					final AnalysisSelection analysisSelection = getAnalysisSelection();
					monitor.worked(1);
					UUID analysisProjectUUID = analysisSelection
							.getAnalysisProject().getInformation().getUUID();
					monitor.worked(1);
					UUID analysisRunUUID = analysisSelection.getAnalysisRun()
							.getInformation().getUUID();
					monitor.worked(1);
					mapValues = client.loadMetricsMapData(analysisProjectUUID,
							analysisRunUUID, mapMetricSelection.getName(),
							mapParameterSelection,
							colorMetricSelection.getName(),
							colorParameterSelection);
					monitor.worked(1);
					monitor.done();
					new UIJob("Draw Metric Map") {

						@Override
						public IStatus runInUIThread(IProgressMonitor monitor) {
							AnalysisFileTree path = analysisSelection
									.getFileTreeNode();
							if (path.isFile()) {
								path = path.getParent();
							}
							showEvaluation(path);
							return Status.OK_STATUS;
						}
					}.schedule();
					;
					return Status.OK_STATUS;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		};
		job.schedule();
	}

	@Override
	public void showEvaluation(AnalysisFileTree path) {
		if (path != null) {
			label.setText(path.getPathFile(false).getPath());
		} else {
			label.setText("");
		}
		AreaMapData data = calculateAreaData(path);
		setColorProvider();
		areaMap.setData(data, mapParameterSelection.getUnit());
	}

	private void setColorProvider() {
		try {
			BundleContext bundleContext = Activator.getDefault().getBundle()
					.getBundleContext();
			String parameterName = colorParameterSelection.getName();
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
	 * @throws EvaluationStoreException
	 */
	private AreaMapData calculateAreaData(AnalysisFileTree path) {
		List<AreaMapData> childAreas = calculateChildAreaMaps(path);
		Double sum = 0.0;
		Object secondaryValue = null;
		String pathName = "";
		if (path != null) {
			pathName = path.getPathFile(false).toString();
			HashId hashId = path.getHashId();
			Map<String, Value<? extends Number>> mapResults = mapValues
					.getMapValues(hashId);
			Map<String, Value<?>> colorResults = mapValues
					.getColorValues(hashId);
			if ((mapResults == null) || (mapResults.size() == 0)) {
				return processAreaWithoutOwnValues(path,
						childAreas.toArray(new AreaMapData[childAreas.size()]));
			}
			for (String codeRangeName : mapResults.keySet()) {
				sum += mapResults.get(codeRangeName).getValue().doubleValue();
				if ((colorResults != null) && (colorResults.size() > 0)) {
					if (secondaryValue == null) {
						secondaryValue = colorResults.get(codeRangeName)
								.getValue();
					}
				}
			}
		}
		return new AreaMapData(pathName, sum, secondaryValue,
				childAreas.toArray(new AreaMapData[childAreas.size()]));
	}

	/**
	 * This method calculates the values for all {@link HashIdFileTree}'s
	 * children.
	 * 
	 * @param mapStore
	 * @param colorStore
	 * @param path
	 * @return
	 * @throws EvaluationStoreException
	 */
	private List<AreaMapData> calculateChildAreaMaps(AnalysisFileTree path) {
		List<AreaMapData> childAreas = new ArrayList<AreaMapData>();
		if (path == null) {
			return childAreas;
		}
		List<AnalysisFileTree> children = path.getChildren();
		for (int i = 0; i < children.size(); i++) {
			AreaMapData areaData = calculateAreaData(children.get(i));
			if (areaData != null) {
				childAreas.add(areaData);
			}
		}
		Collections.sort(childAreas);
		return childAreas;
	}

	private AreaMapData processAreaWithoutOwnValues(AnalysisFileTree path,
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
					mapMetricSelection, mapParameterSelection,
					colorMetricSelection, colorParameterSelection);
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
		mapParameterSelection = settingsDialog.getMapValue();
		colorMetricSelection = settingsDialog.getColorMetric();
		colorParameterSelection = settingsDialog.getColorValue();
		updateView();
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

	@Override
	public void setFocus() {
		areaMap.setFocus();
	}
}
