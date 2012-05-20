package com.puresol.coding.client.charting.rendering;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.controls.VerticalLabel;

/**
 * This class provides a chart canvas for charts with all interactive features
 * available.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart2DCanvas extends Composite implements PaintListener {

    private Chart2D chart2D;
    private final Chart2DRenderer renderer = new Chart2DRenderer();

    private Composite titleComposite;
    private Composite northTitles;
    private Composite eastTitles;
    private Composite southTitles;
    private Composite westTitles;
    private Composite legendComposite;
    private Composite northLegends;
    private Composite eastLegends;
    private Composite southLegends;
    private Composite westLegends;

    public Canvas chart;

    public Chart2DCanvas(Composite parent, int style) {
	super(parent, style);
	initGUI();
    }

    public Chart2DCanvas(Composite parent, int style, Chart2D chart2D) {
	super(parent, style);
	this.chart2D = chart2D;
	initGUI();
    }

    private void initGUI() {
	setLayout(new BorderLayout());

	addTitleComposite();
	addLegendComposite();
	addGraphCanvas();
	this.pack();
    }

    private void addTitleComposite() {
	titleComposite = new Composite(this, SWT.NONE);
	titleComposite.setLayoutData(BorderLayout.CENTER);
	titleComposite.setLayout(new BorderLayout());

	northTitles = new Composite(titleComposite, SWT.NONE);
	northTitles.setLayoutData(BorderLayout.NORTH);
	northTitles.setLayout(new FillLayout(SWT.VERTICAL));

	eastTitles = new Composite(titleComposite, SWT.NONE);
	eastTitles.setLayoutData(BorderLayout.EAST);
	eastTitles.setLayout(new FillLayout(SWT.HORIZONTAL));

	VerticalLabel title = new VerticalLabel(eastTitles, SWT.NONE);
	title.setText("EastTitle");
	FontData fontData = new FontData("Arial", 24, SWT.BOLD);
	Font font = new Font(getDisplay(), fontData);
	title.setFont(font);

	title = new VerticalLabel(eastTitles, SWT.NONE);
	title.setText("E");
	title.setFont(font);

	southTitles = new Composite(titleComposite, SWT.NONE);
	southTitles.setLayoutData(BorderLayout.SOUTH);
	southTitles.setLayout(new FillLayout(SWT.VERTICAL));

	westTitles = new Composite(titleComposite, SWT.NONE);
	westTitles.setLayoutData(BorderLayout.WEST);
	westTitles.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    private void addLegendComposite() {
	legendComposite = new Composite(titleComposite, SWT.NONE);
	legendComposite.setLayoutData(BorderLayout.CENTER);
	legendComposite.setLayout(new BorderLayout());

	northLegends = new Composite(legendComposite, SWT.NONE);
	northLegends.setLayoutData(BorderLayout.NORTH);
	northLegends.setLayout(new FillLayout(SWT.VERTICAL));

	eastLegends = new Composite(legendComposite, SWT.NONE);
	eastLegends.setLayoutData(BorderLayout.EAST);
	eastLegends.setLayout(new FillLayout(SWT.HORIZONTAL));

	southLegends = new Composite(legendComposite, SWT.NONE);
	southLegends.setLayoutData(BorderLayout.SOUTH);
	southLegends.setLayout(new FillLayout(SWT.VERTICAL));

	westLegends = new Composite(legendComposite, SWT.NONE);
	westLegends.setLayoutData(BorderLayout.WEST);
	westLegends.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    private void addGraphCanvas() {
	chart = new Canvas(legendComposite, SWT.NONE);
	chart.setLayoutData(BorderLayout.CENTER);
	chart.addPaintListener(this);
    }

    public Chart2D getChart2D() {
	return chart2D;
    }

    public void setChart2D(Chart2D chart2d) {
	chart2D = chart2d;
    }

    @Override
    public void paintControl(PaintEvent e) {
	renderer.render(e.gc, chart.getClientArea(), chart.getDisplay(),
		chart2D);
    }

}
