package com.puresoltechnologies.purifinity.server.common.job;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.inject.Produces;

/**
 * This class is a simple CDI provider to allow injection of {@link JobOperator}
 * to not to call {@link BatchRuntime#getJobOperator()} everytime.
 * 
 * @author Rick-Rainer Ludwig
 */
public class JobOperatorProvider {

	@Produces
	public JobOperator create() {
		return BatchRuntime.getJobOperator();
	}

}
