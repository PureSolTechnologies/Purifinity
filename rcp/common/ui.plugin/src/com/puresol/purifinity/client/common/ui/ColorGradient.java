package com.puresol.purifinity.client.common.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.RGB;

public class ColorGradient<T extends Number> {

	private final List<T> values = new ArrayList<>();
	private final Map<T, RGB> colorValues = new HashMap<>();

	public void addColor(T value, RGB color) {
		values.add(value);
		colorValues.put(value, color);
		sortValues();
	}

	private void sortValues() {
		Collections.sort(values, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return ColorGradient.compare(o1, o2);
			}

		});
	}

	private static int compare(Number o1, Number o2) {
		double d1 = o1.doubleValue();
		double d2 = o2.doubleValue();
		if (d1 < d2) {
			return -1;
		} else if (d1 > d2) {
			return 1;
		} else {
			return 0;
		}
	}

	public RGB getColor(T value) {
		if (values.isEmpty() || (values.size() < 2)) {
			throw new IllegalStateException(
					"There need to be at least two colors for two different value registered.");
		}
		if (compare(value, values.get(0)) < 0) {
			throw new IllegalArgumentException("The given value '" + value
					+ "' needs is smaller than the minimum value '"
					+ values.get(0) + "'.");
		}
		T startValue = null;
		T endValue = null;
		for (int i = 0; i < values.size() - 1; i++) {
			T v = values.get(i);
			T nextV = values.get(i + 1);
			if ((compare(v, value) <= 0) && (compare(nextV, value) > 0)) {
				startValue = v;
				endValue = values.get(i + 1);
				break;
			}
		}
		if (compare(value, values.get(values.size() - 1)) == 0) {
			return colorValues.get(value);
		}
		if (startValue == null) {
			throw new IllegalArgumentException("The given value '" + value
					+ "' needs is greater than or equal to the max value '"
					+ values.get(0) + "'.");
		}
		RGB minColor = colorValues.get(startValue);
		RGB maxColor = colorValues.get(endValue);
		double start = startValue.doubleValue();
		double end = endValue.doubleValue();
		double v = value.doubleValue();
		double ratio = (v - start) / (end - start);
		int red = minColor.red
				+ (int) Math.round((maxColor.red - minColor.red) * ratio);
		int green = minColor.green
				+ (int) Math.round((maxColor.green - minColor.green) * ratio);
		int blue = minColor.blue
				+ (int) Math.round((maxColor.blue - minColor.blue) * ratio);
		return new RGB(red, green, blue);
	}
}
