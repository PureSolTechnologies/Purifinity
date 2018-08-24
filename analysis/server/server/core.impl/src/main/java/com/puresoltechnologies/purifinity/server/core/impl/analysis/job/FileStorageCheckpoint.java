package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public class FileStorageCheckpoint implements Serializable {

	private static final long serialVersionUID = 8322479408653687180L;

	private final List<SourceCodeLocation> sourceCodeLocations = new ArrayList<>();
	private final long totalItemCount;

	public FileStorageCheckpoint(List<SourceCodeLocation> sourceCodeLocations) {
		this.sourceCodeLocations.addAll(sourceCodeLocations);
		this.totalItemCount = sourceCodeLocations.size();
	}

	public synchronized SourceCodeLocation getNext() {
		if (sourceCodeLocations.size() == 0) {
			return null;
		}
		SourceCodeLocation next = sourceCodeLocations.get(0);
		sourceCodeLocations.remove(0);
		return next;
	}

	public long getTotalItemCount() {
		return totalItemCount;
	}
}
