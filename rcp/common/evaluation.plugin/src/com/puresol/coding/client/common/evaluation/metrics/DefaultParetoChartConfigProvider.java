package com.puresol.coding.client.common.evaluation.metrics;

import org.eclipse.swt.graphics.RGB;

import com.puresol.coding.client.common.chart.renderer.ColorProvider;
import com.puresol.coding.client.common.chart.renderer.ConstantColorProvider;

public class DefaultParetoChartConfigProvider implements
		ChartConfigProvider {

	@Override
	public ColorProvider getColorProvider() {
		return new ConstantColorProvider(new RGB(0, 0, 0), new RGB(128, 128,
				128));
	}

}
