package com.puresoltechnologies.purifinity.client.common.chart;

import java.lang.ref.WeakReference;

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

	/**
	 * This field contains the back reference to a {@link Plot} for which the
	 * colored area is to be drawn. This is needed due to the fact that the
	 * colored area is defined for a range of value which need to be referenced
	 * to a plot to get the correct position.
	 * 
	 * This reference needs to be kept a {@link WeakReference} to get this
	 * colored area also removed as soon as the plot is not valid any more.
	 */
	private final WeakReference<Plot<TX, TY>> plot;
	private final double minX;
	private final double maxX;
	private final double minY;
	private final double maxY;
	private final RGB color;

	public ColoredArea(Plot<TX, TY> plot, double minX, double maxX,
			double minY, double maxY, RGB color) {
		super();
		this.plot = new WeakReference<Plot<TX, TY>>(plot);
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
		this.color = color;
	}

	public Plot<TX, TY> getPlot() {
		return plot.get();
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
