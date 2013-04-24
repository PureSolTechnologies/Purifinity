package com.puresol.coding.client.common.chart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.puresol.coding.client.common.chart.renderer.ColorProvider;
import com.puresol.coding.client.common.chart.renderer.MarkRenderer;

/**
 * This is the central canvas class for interactive charting.
 * 
 * @author Rick-Rainer Ludwig
 */
public class ChartCanvas extends Canvas {

	private final Label title;
	private final Label subTitle;
	private final PlotCanvas plot;
	// private final Canvas legende;
	private final Font titleFont;
	private final Font subTitleFont;

	private Chart2D chart2D;

	public ChartCanvas(Composite parent, int style) {
		super(parent, style);
		FillLayout layout = new FillLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		parent.setLayout(layout);

		setLayout(new ChartLayout());
		setBackground(new Color(getDisplay(), new RGB(255, 255, 255)));
		title = new Label(this, SWT.NONE);
		title.setLayoutData(ChartElement.TITLE);
		title.setAlignment(SWT.CENTER);
		Font defaultFont = title.getFont();
		titleFont = new Font(title.getDisplay(),
				defaultFont.getFontData()[0].getName(),
				(int) (defaultFont.getFontData()[0].getHeight() * 1.5),
				defaultFont.getFontData()[0].getStyle() | SWT.BOLD);
		title.setFont(titleFont);
		subTitle = new Label(this, SWT.NONE);
		subTitle.setLayoutData(ChartElement.SUBTITLE);
		subTitle.setAlignment(SWT.CENTER);
		subTitleFont = new Font(title.getDisplay(),
				defaultFont.getFontData()[0].getName(),
				(int) (defaultFont.getFontData()[0].getHeight() * 1.2),
				defaultFont.getFontData()[0].getStyle() | SWT.BOLD);
		subTitle.setFont(subTitleFont);
		plot = new PlotCanvas(this, SWT.BORDER);
		plot.setLayoutData(ChartElement.PLOT);
		// legende = new Canvas(canvas, SWT.BORDER);
		// legende.setLayoutData(ChartElement.LEGENDE);
	}

	public Chart2D getChart2D() {
		return chart2D;
	}

	public void setChart2D(Chart2D chart2D) {
		this.chart2D = chart2D;
		plot.setChart(chart2D);
		refresh();
	}

	public void setMarkRenderer(Plot<?, ?> plot, MarkRenderer markRenderer) {
		this.plot.setMarkRenderer(plot, markRenderer);
	}

	public void setColorProvider(Plot<?, ?> plot, ColorProvider colorProvider) {
		this.plot.setColorProvider(plot, colorProvider);
	}

	public void refresh() {
		if (chart2D.getTitle() != null) {
			title.setText(chart2D.getTitle());
		}
		if (chart2D.getSubTitle() != null) {
			subTitle.setText(chart2D.getSubTitle());
		}
		plot.redraw();
		layout();
		redraw();
		update();
	}

}