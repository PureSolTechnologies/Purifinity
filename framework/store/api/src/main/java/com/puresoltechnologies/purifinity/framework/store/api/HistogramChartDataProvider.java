package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

/**
 * This is the interface for the data backend for Histogram Chart.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface HistogramChartDataProvider {

	/**
	 * This method reads the values needed for Histogram Charts. The data
	 * returned contains only the value for each code range in the file tree for
	 * each run.
	 * 
	 * <b>The idea behind this:</b> The values loaded are for the whole analysis
	 * file tree. The selection of a new file must not have a new data load to
	 * have a fast feed back.
	 * 
	 * @param analysisProject
	 *            is the project to be looked for.
	 * @param analysisRun
	 *            is the analysis run to be looked for.
	 * @param evaluatorName
	 *            is the name of the evaluator for which the results are looked
	 *            for.
	 * @param parameter
	 *            is the parameter which provides the values to be loaded.
	 * @param codeRangeType
	 *            is the type of the code range. Only the code ranges defined
	 *            here are loaded. A mixture of different code ranges is not
	 *            meaningful.
	 * @return A {@link Map} of {@link HashId} and {@link Value} is returned
	 *         containing the values. The key is the {@link HashId} of the file
	 *         or directory and the value is the {@link Value} of the value to
	 *         be used for the chart.
	 */
	public Map<HashId, List<Value<?>>> loadValues(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType);

}
