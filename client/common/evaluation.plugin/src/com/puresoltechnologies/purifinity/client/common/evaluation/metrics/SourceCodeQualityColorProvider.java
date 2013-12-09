package com.puresoltechnologies.purifinity.client.common.evaluation.metrics;

import org.eclipse.swt.graphics.RGB;

import com.puresoltechnologies.purifinity.client.common.chart.renderer.ColorProvider;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class SourceCodeQualityColorProvider implements ColorProvider {

	@Override
	public RGB getBackgroundColor(Object value) {
		RGB rgb = getColor(value);
		if (rgb != null) {
			return new RGB(rgb.red / 2, rgb.green / 2, rgb.blue / 2);
		} else {
			return null;
		}
	}

	@Override
	public RGB getForegroundColor(Object value) {
		return getColor(value);
	}

	private RGB getColor(Object value) {
		if (value instanceof SourceCodeQuality) {
			SourceCodeQuality quality = (SourceCodeQuality) value;
			switch (quality) {
			case HIGH:
				return new RGB(0, 255, 0);
			case MEDIUM:
				return new RGB(255, 255, 0);
			case LOW:
				return new RGB(255, 0, 0);
			default:
				return null;
			}
		} else {
			return null;
		}
	}
}
