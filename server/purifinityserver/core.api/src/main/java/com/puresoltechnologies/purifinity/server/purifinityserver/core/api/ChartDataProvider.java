package com.puresoltechnologies.purifinity.server.purifinityserver.core.api;

import java.util.UUID;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.ChartData1D;

/**
 * This interface represents a the chart data provider for the client.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface ChartDataProvider {

	/**
	 * This method reads data for a single 1D chart like histogram charts,
	 * pareto charts and so forth.
	 * 
	 * @param analysisProject
	 * @param analysisRun
	 * @param evaluatorName
	 * @param parameter
	 * @param codeRangeType
	 * @return
	 */
	public ChartData1D loadValues(UUID analysisProject, UUID analysisRun,
			String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType);
}
