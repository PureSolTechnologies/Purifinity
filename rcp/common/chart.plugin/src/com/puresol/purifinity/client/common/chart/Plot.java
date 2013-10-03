package com.puresol.purifinity.client.common.chart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents a single plot (aka data set) which is used to be put
 * onto a {@link Chart}.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Plot<TX, TY> {

	private final Axis<TX> xAxis;
	private final Axis<TY> yAxis;
	private final String name;
	private final List<Mark2D<TX, TY>> tuples = new ArrayList<Mark2D<TX, TY>>();

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

	public void add(Mark2D<TX, TY> tuple) {
		tuples.add(tuple);
	}

	public void add(Collection<Mark2D<TX, TY>> tuples) {
		this.tuples.addAll(tuples);
	}

	public List<Mark2D<TX, TY>> getDataPoints() {
		return tuples;
	}
}
