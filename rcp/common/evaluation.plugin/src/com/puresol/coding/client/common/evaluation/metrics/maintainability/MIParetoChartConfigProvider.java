package com.puresol.coding.client.common.evaluation.metrics.maintainability;

import com.puresol.coding.client.common.chart.renderer.ColorProvider;
import com.puresol.coding.client.common.evaluation.metrics.ParetoChartConfigProvider;

public class MIParetoChartConfigProvider implements ParetoChartConfigProvider {

	@Override
	public ColorProvider getColorProvider() {
		return new MIColorProvider();
	}

}
