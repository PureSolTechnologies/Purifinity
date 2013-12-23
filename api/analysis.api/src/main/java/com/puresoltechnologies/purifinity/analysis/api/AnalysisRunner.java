package com.puresoltechnologies.purifinity.analysis.api;

import java.util.concurrent.Callable;

public interface AnalysisRunner extends Callable<Boolean> {

	public AnalysisRun getAnalysisRun() throws AnalysisProjectException;

}
