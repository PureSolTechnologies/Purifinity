package com.puresol.coding.client.common.evaluation.metrics;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.ui.components.AreaMapColorProvider;
import com.puresol.coding.evaluation.api.SourceCodeQuality;

public class SourceCodeQualityColorProvider implements AreaMapColorProvider {

	@Override
	public Color createColor(Device device, Object value) {
		if (value instanceof SourceCodeQuality) {
			SourceCodeQuality quality = (SourceCodeQuality) value;
			switch (quality) {
			case HIGH:
				return new Color(device, new RGB(0, 255, 0));
			case MEDIUM:
				return new Color(device, new RGB(255, 255, 0));
			case LOW:
				return new Color(device, new RGB(255, 0, 0));
			default:
				return null;
			}
		} else {
			return null;
		}
	}
}
