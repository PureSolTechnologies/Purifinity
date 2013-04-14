package com.puresol.coding.client.common.evaluation.metrics.maintainability;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.chart.DataPoint2D;
import com.puresol.coding.client.common.chart.renderer.ColorProvider;

public class MIColorProvider implements ColorProvider {

	private final MIAreaMapColorProvider provider = new MIAreaMapColorProvider();

	@Override
	public Color provideForegroundColor(Device device, DataPoint2D dataPoint) {
		return new Color(device, new RGB(0, 0, 0));
	}

	@Override
	public Color provideBackgroundColor(Device device, DataPoint2D dataPoint) {
		return provider.createColor(device, dataPoint.getPoint().getY());
	}

}
