package com.puresol.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.ViewPart;

import com.puresol.purifinity.client.common.analysis.views.FileAnalysisSelection;
import com.puresol.purifinity.client.common.evaluation.contents.MetricsTableViewer;
import com.puresol.purifinity.client.common.evaluation.utils.EvaluationsTarget;
import com.puresol.purifinity.client.common.ui.actions.PartSettingsCapability;
import com.puresol.purifinity.client.common.ui.actions.RefreshAction;
import com.puresol.purifinity.client.common.ui.actions.Refreshable;
import com.puresol.purifinity.client.common.ui.actions.Reproducable;
import com.puresol.purifinity.client.common.ui.actions.ShowSettingsAction;
import com.puresol.purifinity.client.common.ui.actions.ViewReproductionAction;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;
import com.puresol.purifinity.coding.evaluation.api.EvaluatorFactory;
import com.puresol.purifinity.coding.evaluation.api.Evaluators;

public class MetricsTableView extends ViewPart implements Refreshable,
		ISelectionListener, EvaluationsTarget, PartSettingsCapability,
		Reproducable {

	private FileAnalysisSelection analysisSelection;

	private Color backgroundColor;
	private ScrolledComposite scrolledComposite;
	private Composite container;
	private Label label;

	private Font headerFont;
	private Font sectionFont;

	private ISelectionService selectionService;

	private final List<Label> labels = new ArrayList<Label>();
	private final List<Table> tables = new ArrayList<Table>();
	private final List<MetricsTableViewer> viewers = new ArrayList<MetricsTableViewer>();

	public MetricsTableView() {
	}

	@Override
	public void dispose() {
		selectionService.removeSelectionListener(this);
		backgroundColor.dispose();
		headerFont.dispose();
		sectionFont.dispose();
		super.dispose();
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);

		container = new Composite(scrolledComposite, SWT.BORDER);
		backgroundColor = new Color(container.getDisplay(), new RGB(255, 255,
				255));
		container.setBackground(backgroundColor);

		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.wrap = false;
		container.setLayout(layout);
		label = new Label(container, SWT.NONE);
		label.setBackground(backgroundColor);
		Font font = label.getFont();
		FontData[] fontData = font.getFontData();
		headerFont = new Font(label.getDisplay(), fontData[0].getName(),
				(int) (fontData[0].getHeight() * 1.5), fontData[0].getStyle()
						| SWT.BOLD | SWT.UNDERLINE_SINGLE | SWT.ITALIC);
		label.setFont(headerFont);

		sectionFont = new Font(label.getDisplay(), fontData[0].getName(),
				(int) (fontData[0].getHeight() * 1.2), fontData[0].getStyle()
						| SWT.BOLD | SWT.UNDERLINE_SINGLE | SWT.ITALIC);

		IWorkbenchPartSite site = getSite();
		IWorkbenchWindow workbenchWindow = site.getWorkbenchWindow();
		selectionService = workbenchWindow.getSelectionService();
		selectionService.addSelectionListener(this);

		container.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		scrolledComposite.setContent(container);

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
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void refresh() {
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof FileAnalysisSelection) {
			analysisSelection = (FileAnalysisSelection) selection;
			updateEvaluation();
		}
	}

	private void updateEvaluation() {
		HashIdFileTree path = analysisSelection.getFileTreeNode();
		showEvaluation(path);
	}

	@Override
	public void showEvaluation(HashIdFileTree path) {
		label.setText("Metrics for: " + path.getPathFile(false).getPath());

		Iterator<Label> labelIterator = labels.iterator();
		while (labelIterator.hasNext()) {
			labelIterator.next().dispose();
			labelIterator.remove();
		}
		Iterator<MetricsTableViewer> viewerIterator = viewers.iterator();
		while (viewerIterator.hasNext()) {
			viewerIterator.next().dispose();
			viewerIterator.remove();
		}
		Iterator<Table> tableIterator = tables.iterator();
		while (tableIterator.hasNext()) {
			tableIterator.next().dispose();
			tableIterator.remove();
		}

		Evaluators evaluators = Evaluators.createInstance();
		List<EvaluatorFactory> allMetrics = evaluators.getAllMetrics();
		for (EvaluatorFactory metric : allMetrics) {
			Label label = new Label(container, SWT.NONE);
			label.setBackground(backgroundColor);
			label.setText(metric.getName());
			label.setFont(sectionFont);
			labels.add(label);

			Table table = new Table(container, SWT.BORDER);
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			table.setVisible(true);
			tables.add(table);

			MetricsTableViewer viewer = new MetricsTableViewer(table, metric);
			viewer.setInput(path);
			viewers.add(viewer);
		}
		container.layout(true, true);
		container.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.layout(true, true);
	}

	@Override
	public void showSettings() {
		// TODO Auto-generated method stub
	}

	@Override
	public void applySettings() {
		// TODO Auto-generated method stub
	}

	@Override
	public void closeSettings() {
		// TODO Auto-generated method stub
	}
}
