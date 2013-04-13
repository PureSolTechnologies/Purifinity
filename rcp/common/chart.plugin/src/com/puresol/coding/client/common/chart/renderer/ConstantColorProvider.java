package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.chart.DataPoint2D;

public class ConstantColorProvider implements ColorProvider {

	private final RGB foregroundRGB;
	private final RGB backgroundRGB;

	public ConstantColorProvider(RGB foregroundRGB) {
		this(foregroundRGB, null);
	}

	public ConstantColorProvider(RGB foregroundRGB, RGB backgroundRGB) {
		this.foregroundRGB = foregroundRGB;
		this.backgroundRGB = backgroundRGB;
	}

	@Override
	public Color provideForegroundColor(Device device, DataPoint2D dataPoint) {
		return new Color(device, foregroundRGB);
	}

	@Override
	public Color provideBackgroundColor(Device device, DataPoint2D dataPoint) {
		return new Color(device, backgroundRGB);
	}

}
