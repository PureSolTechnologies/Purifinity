package com.puresol.coding.client.common.evaluation.metrics.maintainability;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.ui.components.AreaMapColorProvider;

public class MIColorProvider implements AreaMapColorProvider {

	@Override
	public Color createColor(Device device, Object value) {
		if (value instanceof Double) {
			double mi = (Double) value;
			if (mi > 85) {
				return new Color(device, new RGB(0, 255, 0));
			}
			if (mi > 65) {
				return new Color(device, new RGB(255, 255, 0));
			}
			return new Color(device, new RGB(255, 0, 0));
		}
		return null;
	}

}
