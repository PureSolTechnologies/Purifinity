package com.puresol.coding.client.common.evaluation.views;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.printing.Printer;

import com.puresol.coding.client.common.chart.ChartCanvas;
import com.puresol.coding.client.common.chart.renderer.ChartRenderer;

public abstract class AbstractMetricChartViewPart extends
		AbstractMetricViewPart {

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

	protected abstract ChartCanvas getChartCanvas();
}
