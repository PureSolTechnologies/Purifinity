package com.puresoltechnologies.purifinity.analysis.api;

import com.puresoltechnologies.commons.misc.CallableProgressObservable;

public interface AnalysisRunner extends
		CallableProgressObservable<AnalysisRunner, Boolean> {

	public AnalysisRun getAnalysisRun() throws AnalysisProjectException;

}
