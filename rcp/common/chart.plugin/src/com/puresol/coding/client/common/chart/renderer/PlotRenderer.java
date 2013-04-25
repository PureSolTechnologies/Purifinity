package com.puresol.coding.client.common.chart.renderer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.puresol.coding.client.common.chart.ColoredArea;
import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.MarkPosition;
import com.puresol.coding.client.common.chart.Plot;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

public class PlotRenderer {

	private final GC gc;
	private final Plot<?, ?> plot;
	private MarkRenderer markRenderer = new CrossMarkRenderer();
	private ColorProvider colorProvider;

	public PlotRenderer(GC gc, Plot<?, ?> plot) {
		this.gc = gc;
		this.plot = plot;
	}

	public MarkRenderer getMarkRenderer() {
		return markRenderer;
	}

	public void setMarkRenderer(MarkRenderer markRenderer) {
		this.markRenderer = markRenderer;
	}

	public ColorProvider getColorProvider() {
		return colorProvider;
	}

	public void setColorProvider(ColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}

	public List<MarkPosition> render(TransformationMatrix2D transformation) {
		renderColoredAreas(transformation);
		List<MarkPosition> markPositions = renderMarks(transformation);
		return markPositions;
	}

	private void renderColoredAreas(TransformationMatrix2D transformation) {
		for (ColoredArea<?, ?> coloredArea : plot.getColoredAreas()) {
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

	private List<MarkPosition> renderMarks(TransformationMatrix2D transformation) {
		Color currentForeground = gc.getForeground();
		Color currentBackground = gc.getBackground();
		List<MarkPosition> markPositions = new ArrayList<MarkPosition>();
		List<?> dataPoints = plot.getDataPoints();
		for (int i = 0; i < dataPoints.size(); i++) {
			DataPoint2D<?, ?> dataPoint = (DataPoint2D<?, ?>) dataPoints.get(i);
			Color foreground = null;
			Color background = null;
			if (colorProvider != null) {
				Object y = dataPoint.getY();
				RGB foregroundRGB = colorProvider.getForegroundColor(y);
				if (foregroundRGB != null) {
					foreground = new Color(gc.getDevice(), foregroundRGB);
					gc.setForeground(foreground);
				}
				RGB backgroundRGB = colorProvider.getBackgroundColor(y);
				if (backgroundRGB != null) {
					background = new Color(gc.getDevice(), backgroundRGB);
					gc.setBackground(background);
				}

			}
			Rectangle markPosition = markRenderer.render(gc, transformation,
					plot, dataPoint.getX(), dataPoint.getY());
			markPositions.add(new MarkPosition(plot, i, markPosition));
			if (foreground != null) {
				foreground.dispose();
			}
			if (background != null) {
				background.dispose();
			}
		}
		gc.setForeground(currentForeground);
		gc.setBackground(currentBackground);
		return markPositions;
	}

}
