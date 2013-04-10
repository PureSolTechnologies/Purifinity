package com.puresol.coding.client.common.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This is the central canvas class for interactive charting.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ChartCanvas {

	private final Canvas canvas;

	private final Label title;
	private final Label subTitle;
	private final Canvas plot;
	private final Canvas legende;

	public ChartCanvas(Composite parent, int style) {
		canvas = new Canvas(parent, style);
		canvas.setLayout(new FillLayout());
		title = new Label(canvas, SWT.NONE);
		subTitle = new Label(canvas, SWT.NONE);
		plot = new Canvas(canvas, SWT.NONE);
		legende = new Canvas(canvas, SWT.NONE);
		plot.setBackground(new Color(legende.getDisplay(), new RGB(255, 128,
				128)));
		legende.setBackground(new Color(legende.getDisplay(), new RGB(255, 255,
				128)));
	}
}