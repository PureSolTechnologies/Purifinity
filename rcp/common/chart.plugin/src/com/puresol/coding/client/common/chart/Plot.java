package com.puresol.coding.client.common.chart;

import java.util.ArrayList;
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
	private final List<DataPoint> tuples = new ArrayList<DataPoint>();

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

	public void add(DataPoint tuple) {
		tuples.add(tuple);
	}

	public List<DataPoint> getTuples() {
		return tuples;
	}
}
