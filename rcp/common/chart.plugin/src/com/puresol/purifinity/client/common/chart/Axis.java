package com.puresol.purifinity.client.common.chart;

import java.util.ArrayList;
import java.util.List;

import com.puresol.commons.math.Parameter;

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

	/**
	 * This method is used to return the position on the axis for the given
	 * value. All values which are compatible to {@link Number} are converted
	 * directly into a {@link Double}. If the given number fits into the
	 * min-max-range of this axis, the value is valid and returned.
	 * 
	 * For all other values the ticks are searched. If a {@link Tick} is found
	 * which has the same value, the tick's position is returned as result.
	 * 
	 * All other cases (value not numerical and not found in ticks, the value is
	 * not in range) are quit with an {@link IllegalStateException}.
	 * 
	 * @param value
	 *            is the value for which the position on the axis is needed.
	 * @return A {@link Double} is returned representing the location of the
	 *         value on this axis.
	 */
	public Double getPosition(Object value) {
		if (Number.class.isAssignableFrom(value.getClass())) {
			return ((Number) value).doubleValue();
		}
		for (Tick<T> tick : ticks) {
			if (tick.getValue().equals(value)) {
				return tick.getPosition();
			}
		}
		throw new IllegalArgumentException("Value '" + value
				+ "' is not suitable for this axis.");
	}

	public static double suggestMin(double min) {
		if (min == 0.0) {
			return 0.0;
		}
		double minSignum = Math.signum(min);
		min = Math.abs(min);
		double magnitute = Math.log10(min);
		int m = (int) (Math.signum(magnitute) * Math.ceil(Math.abs(magnitute)));
		double interval = Math.pow(10.0, m);
		double newMin = interval;
		while (newMin > min) {
			newMin -= interval / 2.0;
		}
		return minSignum * newMin;
	}

	public static double suggestMax(double max) {
		if (max == 0.0) {
			return 0.0;
		}
		double maxSignum = Math.signum(max);
		max = Math.abs(max);
		double magnitute = Math.log10(max);
		int m = (int) (Math.signum(magnitute) * Math.floor(Math.abs(magnitute)));
		double interval = Math.pow(10.0, m);
		double newMax = interval;
		while (newMax < max) {
			newMax += interval / 2.0;
		}
		return maxSignum * newMax;
	}
}
