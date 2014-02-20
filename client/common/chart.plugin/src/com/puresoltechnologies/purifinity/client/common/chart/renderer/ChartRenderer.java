package com.puresoltechnologies.purifinity.client.common.chart.renderer;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.puresoltechnologies.purifinity.client.common.chart.Axis;
import com.puresoltechnologies.purifinity.client.common.chart.AxisDirection;
import com.puresoltechnologies.purifinity.client.common.chart.Chart2D;
import com.puresoltechnologies.purifinity.client.common.chart.ColoredArea;
import com.puresoltechnologies.purifinity.client.common.chart.Mark2D;
import com.puresoltechnologies.purifinity.client.common.chart.MarkPosition;
import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.math.Point2D;
import com.puresoltechnologies.purifinity.client.common.chart.math.TransformationMatrix2D;

public class ChartRenderer {

	/**
	 * This field contains the positions of the different data point marks. The
	 * Map needs to be a {@link WeakHashMap} due to the plots may be invalidated
	 * from the outside without getting informed about this. The
	 * {@link WeakHashMap} avoids a memory leak.
	 */
	private final Map<Plot<?, ?>, List<MarkPosition>> markPositions = new WeakHashMap<Plot<?, ?>, List<MarkPosition>>();
	/**
	 * This field contains the {@link MarkRenderer}s of the different plots. The
	 * Map needs to be a {@link WeakHashMap} due to the plots may be invalidated
	 * from the outside without getting informed about this. The
	 * {@link WeakHashMap} avoids a memory leak.
	 */
	private final Map<Plot<?, ?>, MarkRenderer> markRenderers = new WeakHashMap<Plot<?, ?>, MarkRenderer>();
	/**
	 * This field contains the {@link ColorProvider}s of the different plots.
	 * The Map needs to be a {@link WeakHashMap} due to the plots may be
	 * invalidated from the outside without getting informed about this. The
	 * {@link WeakHashMap} avoids a memory leak.
	 */
	private final Map<Plot<?, ?>, ColorProvider> colorProviders = new WeakHashMap<Plot<?, ?>, ColorProvider>();
	/**
	 * This field contains colored background areas.
	 * 
	 * These {@link ColoredArea} objects are put into this {@link WeakHashMap}
	 * to get the data cleared as soon as the plot becomes unavailable from the
	 * outside.
	 */
	private final Map<Plot<?, ?>, ColoredArea<?, ?>> coloredAreas = new WeakHashMap<Plot<?, ?>, ColoredArea<?, ?>>();

	private Chart2D chart2d;

	private final Font titleFont;
	private final Font subTitleFont;

	public ChartRenderer(Device device) {
		titleFont = new Font(device, "Arial", 12, SWT.BOLD);
		subTitleFont = new Font(device, "Arial", 10, SWT.ITALIC);
	}

	public void dispose() {
		subTitleFont.dispose();
		titleFont.dispose();
	}

	public void setChart2D(Chart2D chart2d) {
		this.chart2d = chart2d;
	}

	public Chart2D getChart2D() {
		return chart2d;
	}

	public void setMarkRenderer(Plot<?, ?> plot, MarkRenderer markRenderer) {
		markRenderers.put(plot, markRenderer);
	}

	public void setColorProvider(Plot<?, ?> plot, ColorProvider colorProvider) {
		colorProviders.put(plot, colorProvider);
	}

	public Map<Plot<?, ?>, List<MarkPosition>> render(GC gc,
			Rectangle clientArea) {

		configureGC(gc);

		markPositions.clear();

		printTitle(gc, clientArea);
		printSubTitle(gc, clientArea);
		drawPlots(gc, clientArea);

		return markPositions;
	}

	private void drawPlots(GC gc, Rectangle clientArea) {
		// Initialize renderer objects...
		AxisRenderer xAxisRenderer = new AxisRenderer(gc, chart2d.getXAxis(),
				chart2d.getYAxis().getMinimum());
		try {
			AxisRenderer yAxisRenderer = new AxisRenderer(gc,
					chart2d.getYAxis(), chart2d.getXAxis().getMinimum());
			try {
				int xAxisWidth = xAxisRenderer.getWidth();
				int yAxisWidth = yAxisRenderer.getWidth();

				clientArea.x += yAxisWidth;
				clientArea.width -= yAxisWidth;
				clientArea.height -= xAxisWidth;

				/*
				 * Due to the missing fractional pixel drawing facilities in
				 * Eclipse we need to transform everything on our own. The
				 * transformation matrix is created to have a mathematical
				 * correct drawing.
				 */
				TransformationMatrix2D transformMatrix2d = new TransformationMatrix2D();
				// move origin to canvas center
				transformMatrix2d.translate(
						clientArea.x + clientArea.width / 2, clientArea.y
								+ clientArea.height / 2);
				// let y axis point upright
				transformMatrix2d.mirror(AxisDirection.X);

				Axis<?> xAxis = chart2d.getXAxis();
				Axis<?> yAxis = chart2d.getYAxis();

				double rangeX = xAxis.getMaximum() - xAxis.getMinimum();
				double rangeY = yAxis.getMaximum() - yAxis.getMinimum();

				// scale to use the values from dataset to paint
				double scaleX = clientArea.width / rangeX;
				double scaleY = clientArea.height / rangeY;
				transformMatrix2d.scale(scaleX, scaleY);

				// move the center of axes to center of canvas
				double centerX = (xAxis.getMinimum() + xAxis.getMaximum()) / 2.0;
				double centerY = (yAxis.getMinimum() + yAxis.getMaximum()) / 2.0;
				transformMatrix2d.translate(-centerX, -centerY);

				renderColoredAreas(gc, transformMatrix2d);

				xAxisRenderer.render(transformMatrix2d);
				yAxisRenderer.render(transformMatrix2d);

				for (Plot<?, ?> plot : chart2d.getPlots()) {
					PlotRenderer plotRenderer = new PlotRenderer(gc, plot);
					MarkRenderer markRenderer = markRenderers.get(plot);
					if (markRenderer != null) {
						plotRenderer.setMarkRenderer(markRenderer);
					}
					ColorProvider colorProvider = colorProviders.get(plot);
					if (colorProvider != null) {
						plotRenderer.setColorProvider(colorProvider);
					}
					List<MarkPosition> positions = plotRenderer
							.render(transformMatrix2d);
					markPositions.put(plot, positions);
				}

			} finally {
				yAxisRenderer.dispose();
			}
		} finally {
			xAxisRenderer.dispose();
		}
	}

	private void renderColoredAreas(GC gc, TransformationMatrix2D transformation) {
		for (ColoredArea<?, ?> coloredArea : coloredAreas.values()) {
			RGB rgb = coloredArea.getColor();
			Color color = new Color(gc.getDevice(), rgb);
			try {
				Point2D pointUL = new Point2D(coloredArea.getMinX(),
						coloredArea.getMinY());
				Point2D pointLR = new Point2D(coloredArea.getMaxX(),
						coloredArea.getMaxY());
				pointUL = transformation.transform(pointUL);
				pointLR = transformation.transform(pointLR);
				gc.setBackground(color);
				gc.fillRectangle((int) pointUL.getX(), (int) pointUL.getY(),
						(int) (pointLR.getX() - pointUL.getX()),
						(int) (pointLR.getY() - pointUL.getY()));
			} finally {
				color.dispose();
			}
		}
	}

	private void printTitle(GC gc, Rectangle clientArea) {
		Font currentFont = gc.getFont();
		gc.setFont(titleFont);
		FontMetrics fontMetrics = gc.getFontMetrics();
		int titleHeight = fontMetrics.getHeight();
		int averageCharWidth = fontMetrics.getAverageCharWidth();

		String title = chart2d.getTitle();
		gc.drawText(title, clientArea.x
				+ (clientArea.width - title.length() * averageCharWidth) / 2,
				clientArea.y);

		clientArea.y += titleHeight;
		clientArea.height -= titleHeight;
		gc.setFont(currentFont);
	}

	private void printSubTitle(GC gc, Rectangle clientArea) {
		Font currentFont = gc.getFont();
		try {
			gc.setFont(subTitleFont);
			FontMetrics fontMetrics = gc.getFontMetrics();
			int titleHeight = fontMetrics.getHeight();
			int averageCharWidth = fontMetrics.getAverageCharWidth();

			String subTitle = chart2d.getSubTitle();
			gc.drawText(subTitle,
					clientArea.x
							+ (clientArea.width - subTitle.length()
									* averageCharWidth) / 2, clientArea.y);

			clientArea.y += titleHeight;
			clientArea.height -= titleHeight;
		} finally {
			gc.setFont(currentFont);
		}
	}

	private void configureGC(GC gc) {
		gc.setTextAntialias(SWT.ON);
		gc.setAntialias(SWT.ON);
	}

	public Mark2D<?, ?> getDataPointAt(int x, int y) {
		for (Plot<?, ?> plot : markPositions.keySet()) {
			for (MarkPosition markPosition : markPositions.get(plot)) {
				Rectangle position = markPosition.getPosition();
				if ((x >= position.x) && (y >= position.y)
						&& (x <= position.x + position.width)
						&& (y <= position.y + position.height)) {
					int index = markPosition.getDataPointIndex();
					return plot.getDataPoints().get(index);
				}
			}
		}
		return null;
	}

	public void addColoredArea(ColoredArea<?, ?> coloredArea) {
		coloredAreas.put(coloredArea.getPlot(), coloredArea);
	}
}
