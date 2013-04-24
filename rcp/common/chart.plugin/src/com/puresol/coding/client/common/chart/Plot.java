package com.puresol.coding.client.common.chart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents a single dataset which is used to be put onto a
 * {@link Chart}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Plot<TX, TY> {

	private final Axis<TX> xAxis;
	private final Axis<TY> yAxis;
	private final String name;
	private final List<DataPoint2D<TX, TY>> tuples = new ArrayList<DataPoint2D<TX, TY>>();
	private final List<ColoredArea<TX, TY>> coloredAreas = new ArrayList<ColoredArea<TX, TY>>();

	public Plot(Axis<TX> xAxis, Axis<TY> yAxis, String name) {
		super();
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.name = name;
	}

	public Axis<TX> getXAxis() {
		return xAxis;
	}

	public Axis<TY> getYAxis() {
		return yAxis;
	}

	public String getName() {
		return name;
	}

	public void add(DataPoint2D<TX, TY> tuple) {
		tuples.add(tuple);
	}

	public void add(Collection<DataPoint2D<TX, TY>> tuples) {
		this.tuples.addAll(tuples);
	}

	public List<DataPoint2D<TX, TY>> getDataPoints() {
		return tuples;
	}

	public void addColoredArea(ColoredArea<TX, TY> coloredArea) {
		coloredAreas.add(coloredArea);
	}

	public List<ColoredArea<TX, TY>> getColoredAreas() {
		return coloredAreas;
	}
}
