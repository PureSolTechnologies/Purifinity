package com.puresoltechnologies.purifinity.server.purifinityserver.socket.api;

import java.io.IOException;
import java.util.UUID;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.HistogramChartData;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.ParetoChartData;
import com.puresoltechnologies.purifinity.server.purifinityserver.domain.PurifinityServerStatus;

/**
 * This is the official interface to the Purifinity server for the client
 * implementation.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface PurifinityServerClient extends AutoCloseable {

	@Override
	public void close() throws IOException;

	/**
	 * This method requests a new Purifinity Server status which is sent to all
	 * listeners.
	 * 
	 * @throws IOException
	 *             is throw in cases of IO issues.
	 */
	public PurifinityServerStatus getServerStatus() throws IOException;

	/**
	 * 
	 * @param analysisProject
	 * @param analysisRun
	 * @param evaluatorName
	 * @param parameter
	 * @param codeRangeType
	 * @return
	 * @throws IOException
	 */
	public HistogramChartData loadHistogramChartData(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) throws IOException;

	/**
	 * 
	 * @param analysisProject
	 * @param analysisRun
	 * @param evaluatorName
	 * @param parameter
	 * @param codeRangeType
	 * @return
	 * @throws IOException
	 */
	public ParetoChartData loadParetoChartData(UUID analysisProject,
			UUID analysisRun, String evaluatorName, Parameter<?> parameter,
			CodeRangeType codeRangeType) throws IOException;
}
