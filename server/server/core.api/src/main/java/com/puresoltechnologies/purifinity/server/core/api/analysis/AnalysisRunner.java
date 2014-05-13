package com.puresoltechnologies.purifinity.server.core.api.analysis;

import com.puresoltechnologies.commons.misc.CallableProgressObservable;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisProjectException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;

public interface AnalysisRunner extends
		CallableProgressObservable<AnalysisRunner, Boolean> {

	public AnalysisRun getAnalysisRun() throws AnalysisProjectException;

}
