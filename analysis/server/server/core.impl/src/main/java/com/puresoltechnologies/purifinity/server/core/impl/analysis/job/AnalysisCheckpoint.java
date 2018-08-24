package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;

public class AnalysisCheckpoint implements Serializable {

	private static final long serialVersionUID = 8322479408653687180L;

	private final Map<SourceCodeLocation, FileInformation> sourceCodeLocations = new HashMap<>();
	private final long totalItemCount;

	public AnalysisCheckpoint(
			Map<SourceCodeLocation, FileInformation> sourceCodeLocations) {
		this.sourceCodeLocations.putAll(sourceCodeLocations);
		this.totalItemCount = sourceCodeLocations.size();
	}

	public synchronized StoredFile getNext() {
		if (sourceCodeLocations.size() == 0) {
			return null;
		}
		Entry<SourceCodeLocation, FileInformation> next = sourceCodeLocations
				.entrySet().iterator().next();
		sourceCodeLocations.remove(next.getKey());
		return new StoredFile(next.getKey(), next.getValue());
	}

	public long getTotalItemCount() {
		return totalItemCount;
	}
}
