package com.puresol.coding.client.common.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.puresol.coding.client.common.chart.renderer.ConstantColorProvider;
import com.puresol.coding.client.common.chart.renderer.MarkRenderer;

/**
 * This is the central canvas class for interactive charting.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ChartCanvas {

	private final Canvas canvas;

	private final Label title;
	private final Label subTitle;
	private final PlotCanvas plot;
	private final Canvas legende;
	private final Font titleFont;
	private final Font subTitleFont;

	private Chart2D chart2D;

	public ChartCanvas(Composite parent, int style) {
		canvas = new Canvas(parent, style);
		canvas.setLayout(new ChartLayout());
		title = new Label(canvas, SWT.NONE);
		title.setLayoutData(ChartElement.TITLE);
		title.setAlignment(SWT.CENTER);
		Font defaultFont = title.getFont();
		titleFont = new Font(title.getDisplay(),
				defaultFont.getFontData()[0].getName(),
				(int) (defaultFont.getFontData()[0].getHeight() * 1.5),
				defaultFont.getFontData()[0].getStyle() | SWT.BOLD);
		title.setFont(titleFont);
		subTitle = new Label(canvas, SWT.NONE);
		subTitle.setLayoutData(ChartElement.SUBTITLE);
		subTitle.setAlignment(SWT.CENTER);
		subTitleFont = new Font(title.getDisplay(),
				defaultFont.getFontData()[0].getName(),
				(int) (defaultFont.getFontData()[0].getHeight() * 1.2),
				defaultFont.getFontData()[0].getStyle() | SWT.BOLD);
		subTitle.setFont(subTitleFont);
		plot = new PlotCanvas(canvas, SWT.BORDER);
		plot.setLayoutData(ChartElement.PLOT);
		legende = new Canvas(canvas, SWT.BORDER);
		legende.setLayoutData(ChartElement.LEGENDE);
	}

	public Chart2D getChart2D() {
		return chart2D;
	}

	public void setChart2D(Chart2D chart2D) {
		this.chart2D = chart2D;
		if (chart2D.getTitle() != null) {
			title.setText(chart2D.getTitle());
		}
		if (chart2D.getSubTitle() != null) {
			subTitle.setText(chart2D.getSubTitle());
		}
		plot.setChart(chart2D);
	}

	public void setMarkRenderer(Plot<?, ?> plot, MarkRenderer markRenderer) {
		this.plot.setMarkRenderer(plot, markRenderer);
	}

	public void setColorProvider(Plot<String, Double> plot,
			ConstantColorProvider colorProvider) {
		this.plot.setColorProvider(plot, colorProvider);
	}

}