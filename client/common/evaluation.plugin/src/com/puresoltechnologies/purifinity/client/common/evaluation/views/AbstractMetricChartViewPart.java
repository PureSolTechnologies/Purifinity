package com.puresoltechnologies.purifinity.client.common.evaluation.views;

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

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisProject;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisRun;
import com.puresoltechnologies.purifinity.client.common.analysis.views.AnalysisSelection;
import com.puresoltechnologies.purifinity.client.common.chart.ChartCanvas;
import com.puresoltechnologies.purifinity.client.common.chart.renderer.ChartRenderer;

/**
 * This class represents a view part containing metrics as chart.
 * 
 * @author Rick-Rainer Ludwig
 */
public abstract class AbstractMetricChartViewPart extends
		AbstractMetricViewPart implements MouseListener, ISelectionProvider {

	private final List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();
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
	public final void print(Printer printer, String printJobName) {
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
				AnalysisProject analysis = selection.getAnalysisProject();
				AnalysisRun analysisRun = selection.getAnalysisRun();
				if (reference instanceof AnalysisFileTree) {
					AnalysisFileTree node = (AnalysisFileTree) reference;
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
	public final void addSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListener.add(listener);
	}

	@Override
	public final void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		selectionChangedListener.remove(listener);
	}

	@Override
	public final ISelection getSelection() {
		return getAnalysisSelection();
	}

	@Override
	protected final void setAnalysisSelection(
			AnalysisSelection analysisSelection) {
		super.setAnalysisSelection(analysisSelection);
		setSelection(analysisSelection);
	}

	@Override
	public final void setSelection(ISelection selection) {
		for (ISelectionChangedListener listener : selectionChangedListener) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
	}
}
