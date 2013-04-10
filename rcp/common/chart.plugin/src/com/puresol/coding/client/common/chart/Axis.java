package com.puresol.coding.client.common.chart;

import java.util.ArrayList;
import java.util.List;

import com.puresol.utils.math.Parameter;

public class Axis<T> {

	private final AxisDirection direction;
	private final Parameter<T> parameter;
	private final List<T> ticks = new ArrayList<T>();

	public Axis(AxisDirection direction, Parameter<T> parameter) {
		super();
		this.direction = direction;
		this.parameter = parameter;
	}

	public Parameter<T> getParameter() {
		return parameter;
	}

	public AxisDirection getDirection() {
		return direction;
	}

	public void addTick(T tick) {
		ticks.add(tick);
	}

}
