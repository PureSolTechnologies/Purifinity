package com.puresoltechnologies.purifinity.client.common.evaluation.metrics;

import org.eclipse.swt.graphics.RGB;

import com.puresoltechnologies.purifinity.client.common.chart.renderer.ColorProvider;
import com.puresoltechnologies.purifinity.client.common.ui.ColorGradient;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;

public class QualityLevelColorProvider implements ColorProvider {

	private static final ColorGradient<Double> gradient = new ColorGradient<>();
	static {
		gradient.addColor(0.0, new RGB(255, 0, 0));
		gradient.addColor(0.5, new RGB(255, 255, 0));
		gradient.addColor(1.0, new RGB(0, 255, 0));
	}

	@Override
	public RGB getForegroundColor(Object value) {
		return getColor(value);
	}

	@Override
	public RGB getBackgroundColor(Object value) {
		RGB rgb = getColor(value);
		if (rgb != null) {
			return new RGB(rgb.red / 2, rgb.green / 2, rgb.blue / 2);
		} else {
			return null;
		}
	}

	private RGB getColor(Object value) {
		if (value instanceof QualityLevel) {
			QualityLevel qualityLevel = (QualityLevel) value;
			return gradient.getColor(qualityLevel.getLevel());
		}
		return null;
	}
}
