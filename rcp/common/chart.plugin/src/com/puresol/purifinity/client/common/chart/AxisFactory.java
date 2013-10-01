package com.puresol.purifinity.client.common.chart;

import com.puresol.commons.math.Parameter;

public class AxisFactory {

	public static Axis<Double> createDoubleValueAxis(AxisDirection direction,
			Parameter<Double> parameter, double min, double max,
			double tickWidth, int numSubTicks, int precision) {
		Axis<Double> axis = new Axis<Double>(direction, parameter);
		axis.setMinimum(min);
		axis.setMaximum(max);
		if (tickWidth > 0) {
			String formatString = "%" + (precision) + "." + precision + "f";
			for (double tick = min; tick <= max; tick += tickWidth) {
				String label = String.format(formatString, tick);
				axis.addTick(new Tick<Double>(TickType.MAJOR, tick, tick, label));
				if ((numSubTicks > 0) && (tick < max)) {
					double subTickWidth = tickWidth / (numSubTicks + 1);
					for (int i = 1; i <= numSubTicks; i++) {
						double position = tick + i * subTickWidth;
						String subLabel = String.format(formatString, position);
						axis.addTick(new Tick<Double>(TickType.MINOR, position,
								position, subLabel));
					}
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
			if ((numSubTicks > 0) && (tick < max)) {
				double subTickWidth = (double) tickWidth / (numSubTicks + 1);
				for (int i = 1; i <= numSubTicks; i++) {
					Integer position = (int) (tick + i * subTickWidth);
					axis.addTick(new Tick<Integer>(TickType.MINOR, position,
							position, String.valueOf(position)));
				}
			}
		}
		return axis;
	}

	public static Axis<String> createCategoryAxis(AxisDirection direction,
			Parameter<String> parameter, String... categories) {
		Axis<String> axis = new Axis<String>(direction, parameter);
		axis.setMinimum(0);
		axis.setMaximum(categories.length);
		for (int tick = 0; tick < categories.length; tick += 1) {
			axis.addTick(new Tick<String>(TickType.MAJOR, categories[tick],
					tick + 0.5, categories[tick]));
		}
		return axis;
	}
}
