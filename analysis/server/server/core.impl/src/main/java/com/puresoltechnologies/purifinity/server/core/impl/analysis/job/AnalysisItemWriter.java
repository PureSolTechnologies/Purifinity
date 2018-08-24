package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

@Named("AnalysisItemWriter")
public class AnalysisItemWriter extends AbstractItemWriter {

	@Override
	public void writeItems(List<Object> items) throws Exception {
		// Intentionally left empty
	}

}
