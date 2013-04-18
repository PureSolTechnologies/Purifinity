package com.puresol.coding.client.common.chart.renderer;

import org.eclipse.swt.graphics.RGB;

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
	public RGB getForegroundColor(Object value) {
		return foregroundRGB;
	}

	@Override
	public RGB getBackgroundColor(Object value) {
		return backgroundRGB;
	}

}
