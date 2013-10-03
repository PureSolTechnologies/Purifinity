package com.puresol.purifinity.client.common.evaluation.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.printing.Printer;
import org.eclipse.swt.widgets.Composite;

import com.puresol.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresol.purifinity.client.common.chart.ChartCanvas;
import com.puresol.purifinity.client.common.chart.renderer.ChartRenderer;
import com.puresol.purifinity.coding.analysis.api.AnalysisProject;
import com.puresol.purifinity.coding.analysis.api.AnalysisRun;
import com.puresol.purifinity.coding.analysis.api.HashIdFileTree;

public abstract class AbstractMetricChartViewPart extends
		AbstractMetricViewPart implements MouseListener, ISelectionProvider {

	private final List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();
	private AnalysisSelection analysisSelection;
	private ChartCanvas chartCanvas;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		chartCanvas = new ChartCanvas(parent, SWT.NONE);
		chartCanvas.addMouseListener(this);

		getSite().setSelectionProvider(this);

		super.createPartControl(parent);
	}

	@Override
	public final void setFocus() {
		getChartCanvas().setFocus();
	}

	protected final ChartCanvas getChartCanvas() {
		return chartCanvas;
	}

	@Override
	public void print(Printer printer, String printJobName) {
		printer.startJob(printJobName);
		try {
			GC gc = new GC(printer);
			try {
				printer.startPage();
				Rectangle clientArea = printer.getClientArea();

				ChartRenderer chartRenderer = getChartCanvas()
						.getChartRenderer();
				chartRenderer.render(gc, clientArea);

				printer.endPage();
			} finally {
				gc.dispose();
			}
		} finally {
			printer.endJob();
		}
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// intentionally left blank
	}

	@Override
	public void mouseDown(MouseEvent e) {
		if (e.getSource() == chartCanvas) {
			int x = e.x;
			int y = e.y;
			Object reference = chartCanvas.getReference(x, y);
			AnalysisSelection selection = getAnalysisSelection();
			if ((reference != null) && (selection != null)) {
				AnalysisProject analysis = selection.getAnalysis();
				AnalysisRun analysisRun = selection.getAnalysisRun();
				if (reference instanceof HashIdFileTree) {
					HashIdFileTree node = (HashIdFileTree) reference;
					setSelection(new AnalysisSelection(analysis, analysisRun,
							node));
				}
			}
		}
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// intentionally left blank
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListener.add(listener);
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListener.remove(listener);
	}

	@Override
	public ISelection getSelection() {
		return analysisSelection;
	}

	@Override
	public void setSelection(ISelection selection) {
		analysisSelection = (AnalysisSelection) selection;
		for (ISelectionChangedListener listener : selectionChangedListener) {
			listener.selectionChanged(new SelectionChangedEvent(this,
					analysisSelection));
		}
	}

}
