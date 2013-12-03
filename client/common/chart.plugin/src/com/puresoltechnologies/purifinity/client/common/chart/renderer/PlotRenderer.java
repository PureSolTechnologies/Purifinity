package com.puresoltechnologies.purifinity.client.common.chart.renderer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.puresoltechnologies.purifinity.client.common.chart.Mark2D;
import com.puresoltechnologies.purifinity.client.common.chart.MarkPosition;
import com.puresoltechnologies.purifinity.client.common.chart.Plot;
import com.puresoltechnologies.purifinity.client.common.chart.math.TransformationMatrix2D;

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
		Color currentForeground = gc.getForeground();
		Color currentBackground = gc.getBackground();
		List<MarkPosition> markPositions = new ArrayList<MarkPosition>();
		List<?> dataPoints = plot.getDataPoints();
		for (int i = 0; i < dataPoints.size(); i++) {
			Mark2D<?, ?> dataPoint = (Mark2D<?, ?>) dataPoints.get(i);
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
