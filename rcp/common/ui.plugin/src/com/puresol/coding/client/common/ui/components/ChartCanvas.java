package com.puresol.coding.client.common.ui.components;

import org.eclipse.birt.chart.device.IDeviceRenderer;
import org.eclipse.birt.chart.factory.GeneratedChartState;
import org.eclipse.birt.chart.factory.Generator;
import org.eclipse.birt.chart.factory.RunTimeContext;
import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.attribute.Bounds;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.util.PluginSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class ChartCanvas extends Canvas implements PaintListener,
		ControlListener {

	// Image rendered from the chart
	private Image chartBuffer;

	// Birt chart object
	private Chart chart = null;

	public ChartCanvas(Composite parent) {
		super(parent, SWT.NONE);
		addPaintListener(this);
		addControlListener(this);

	}

	public void render() {
		updateCanvas();
		redraw();
	}

	private void updateCanvas() {
		if (chart == null) {
			chartBuffer = null;
			return;
		}
		Rectangle rectArea = getClientArea();
		if ((rectArea.width == 0) || (rectArea.height == 0)) {
			return;
		}

		// We create an image of the size of the available area
		chartBuffer = new Image(Display.getDefault(), rectArea);

		// New graphics context to draw on the buffer image
		GC gc = new GC(chartBuffer);
		try {

			// We set the image colour to white
			gc.setBackground(Display.getDefault().getSystemColor(
					SWT.COLOR_WHITE));
			gc.fillRectangle(chartBuffer.getBounds());

			// Bounds to give to the engine when we create the chart
			Bounds chartBounds = BoundsImpl.create(0, 0, rectArea.width,
					rectArea.height);

			// Create a new device renderer to integrate in a SWT environment
			IDeviceRenderer deviceRenderer = PluginSettings.instance()
					.getDevice("dv.SWT");
			try {
				// Assign the graphic context to the device renderer
				deviceRenderer
						.setProperty(IDeviceRenderer.GRAPHICS_CONTEXT, gc);

				// Scale the bounds from pixels to point (?)
				chartBounds.scale(72d / deviceRenderer.getDisplayServer()
						.getDpiResolution());

				// Create the chart generator
				Generator chartGenerator = Generator.instance();
				RunTimeContext runTimeContext = new RunTimeContext();

				GeneratedChartState chartState = chartGenerator.build(
						deviceRenderer.getDisplayServer(), chart, chartBounds,
						null, runTimeContext, null);
				chartGenerator.render(deviceRenderer, chartState);
			} finally {
				deviceRenderer.dispose();
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		} finally {
			gc.dispose();
		}
	}

	// When SWT asks to repaint the area, we paint the buffer
	@Override
	public void paintControl(PaintEvent e) {
		if ((chart != null) && (chartBuffer != null)) {
			GC gc = e.gc;
			gc.drawImage(chartBuffer, 0, 0);
		}
	}

	@Override
	public void controlMoved(ControlEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controlResized(ControlEvent e) {
		render();
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
		render();
	}

}