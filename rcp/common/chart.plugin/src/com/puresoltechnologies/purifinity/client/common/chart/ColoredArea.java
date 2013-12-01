package com.puresoltechnologies.purifinity.client.common.chart;

import org.eclipse.swt.graphics.RGB;

/**
 * This interface is to be implemented for all classes representing a colored
 * area. The area is arranged along the axes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 * @param <T1>
 *            is the type of the x axis values.
 * @param <T2>
 *            is the type of the y axis values.
 */
public class ColoredArea<TX, TY> {

	private final Plot<TX, TY> plot;
	private final double minX;
	private final double maxX;
	private final double minY;
	private final double maxY;
	private final RGB color;

	public ColoredArea(Plot<TX, TY> plot, double minX, double maxX,
			double minY, double maxY, RGB color) {
		super();
		this.plot = plot;
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.color = color;
	}

	public Plot<TX, TY> getPlot() {
		return plot;
	}

	public double getMinX() {
		return minX;
	}

	public double getMaxX() {
		return maxX;
	}

	public double getMinY() {
		return minY;
	}

	public double getMaxY() {
		return maxY;
	}

	public RGB getColor() {
		return color;
	}

}
