package com.puresol.coding.client.common.evaluation.metrics.maintainability;

import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.chart.renderer.ColorProvider;

public class MIColorProvider implements ColorProvider {

	@Override
	public RGB getForegroundColor(Object value) {
		RGB rgb = getColor(value);
		if (rgb != null) {
			return new RGB(rgb.red / 2, rgb.green / 2, rgb.blue / 2);
		} else {
			return null;
		}
	}

	@Override
	public RGB getBackgroundColor(Object value) {
		return getColor(value);
	}

	private RGB getColor(Object value) {
		if (value instanceof Double) {
			double mi = (Double) value;
			if (mi > 85) {
				return new RGB(0, 255, 0);
			}
			if (mi > 65) {
				return new RGB(255, 255, 0);
			}
			return new RGB(255, 0, 0);
		}
		return null;
	}
}
