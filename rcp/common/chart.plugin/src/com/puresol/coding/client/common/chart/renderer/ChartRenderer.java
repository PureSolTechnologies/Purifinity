package com.puresol.coding.client.common.chart.renderer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.chart.Chart2D;
import com.puresol.coding.client.common.chart.ColoredArea;
import com.puresol.coding.client.common.chart.math.Point2D;
import com.puresol.coding.client.common.chart.math.TransformationMatrix2D;

public class ChartRenderer {

	private final Chart2D chart;

	private final List<ColoredArea> coloredAreas = new ArrayList<ColoredArea>();

	public ChartRenderer(Chart2D chart) {
		this.chart = chart;
	}

	public void addColoredArea(ColoredArea coloredArea) {
		coloredAreas.add(coloredArea);
	}

	public List<ColoredArea> getColoredAreas() {
		return coloredAreas;
	}

	public void render(GC gc, TransformationMatrix2D transformation) {
		for (ColoredArea coloredArea : coloredAreas) {
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

}
