package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import com.puresoltechnologies.purifinity.client.common.chart.renderer.ColorProvider;
import com.puresoltechnologies.purifinity.client.common.evaluation.metrics.ChartConfigProvider;

public class MIParetoChartConfigProvider implements ChartConfigProvider {

	@Override
	public ColorProvider getColorProvider() {
		return new MIColorProvider();
	}

}
