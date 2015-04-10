package com.puresoltechnologies.purifinity.server.core.api;

import java.util.UUID;

import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.server.domain.HistogramChartData;
import com.puresoltechnologies.purifinity.server.domain.MetricsMapData;
import com.puresoltechnologies.purifinity.server.domain.ParetoChartData;

/**
 * This interface represents a the chart data provider for the client.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface ChartDataProvider {

	/**
	 * This method reads data for a single histogram chart.
	 * 
	 * @param analysisProject
	 * @param analysisRun
	 * @param evaluatorName
	 * @param parameter
	 * @param codeRangeType
	 * @return
	 */
	public HistogramChartData loadHistogramChartData(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType);

	/**
	 * This method reads data for a single pareto chart.
	 * 
	 * @param analysisProject
	 * @param analysisRun
	 * @param evaluatorName
	 * @param parameter
	 * @param codeRangeType
	 * @return
	 */
	public ParetoChartData loadParetoChartData(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType);

	public MetricsMapData loadMapValues(UUID analysisProject, UUID analysisRun,
			String mapEvaluatorName, Parameter<?> mapParameter,
			String colorEvaluatorName, Parameter<?> colorParameter);
}
