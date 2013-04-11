package com.puresol.coding.client.common.chart;

import java.util.ArrayList;
import java.util.List;

import com.puresol.utils.math.Parameter;

public class Axis<T> {

	private final AxisDirection direction;
	private final Parameter<T> parameter;
	private final List<Tick<T>> ticks = new ArrayList<Tick<T>>();

	private double minimum = 0.0;
	private double maximum = 1.0;

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

	public void addTick(Tick<T> tick) {
		ticks.add(tick);
	}

	public List<Tick<T>> getTicks() {
		return ticks;
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}

}
