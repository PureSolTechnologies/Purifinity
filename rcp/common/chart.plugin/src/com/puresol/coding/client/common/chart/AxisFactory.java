package com.puresol.coding.client.common.chart;

import com.puresol.utils.math.Parameter;

public class AxisFactory {

	public static Axis<Double> createDoubleValueAxis(AxisDirection direction,
			Parameter<Double> parameter, double min, double max,
			double tickWidth, int numSubTicks) {
		Axis<Double> axis = new Axis<Double>(direction, parameter);
		axis.setMinimum(min);
		axis.setMaximum(max);
		for (double tick = min; tick <= max; tick += tickWidth) {
			axis.addTick(new Tick<Double>(TickType.MAJOR, tick, tick, String
					.valueOf(tick)));
			if (numSubTicks > 0) {
				double subTickWidth = tickWidth / (numSubTicks + 1);
				for (int i = 0; i < numSubTicks; i++) {
					double position = tick + i * subTickWidth;
					axis.addTick(new Tick<Double>(TickType.MINOR, position,
							position, String.valueOf(position)));
				}
			}
		}
		return axis;
	}

	public static Axis<Integer> createIntegerValueAxis(AxisDirection direction,
			Parameter<Integer> parameter, int min, int max, int tickWidth,
			int numSubTicks) {
		Axis<Integer> axis = new Axis<Integer>(direction, parameter);
		axis.setMinimum(min);
		axis.setMaximum(max);
		for (int tick = min; tick <= max; tick += tickWidth) {
			axis.addTick(new Tick<Integer>(TickType.MAJOR, tick, tick, String
					.valueOf(tick)));
			if (numSubTicks > 0) {
				double subTickWidth = (double) tickWidth / (numSubTicks + 1);
				for (int i = 0; i < numSubTicks; i++) {
					Integer position = (int) (tick + i * subTickWidth);
					axis.addTick(new Tick<Integer>(TickType.MINOR, position,
							position, String.valueOf(position)));
				}
			}
		}
		return axis;
	}
}
