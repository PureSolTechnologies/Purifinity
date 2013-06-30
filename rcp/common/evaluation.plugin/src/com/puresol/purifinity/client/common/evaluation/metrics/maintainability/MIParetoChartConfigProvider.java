package com.puresol.purifinity.client.common.evaluation.metrics.maintainability;

import com.puresol.purifinity.client.common.chart.renderer.ColorProvider;
import com.puresol.purifinity.client.common.evaluation.metrics.ChartConfigProvider;

public class MIParetoChartConfigProvider implements ChartConfigProvider {

	@Override
	public ColorProvider getColorProvider() {
		return new MIColorProvider();
	}

}
