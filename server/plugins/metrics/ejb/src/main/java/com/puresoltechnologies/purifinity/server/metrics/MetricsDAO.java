package com.puresoltechnologies.purifinity.server.metrics;

import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;

public interface MetricsDAO<MetricsType> {

    public void storeFileResults(HashId hashId,
	    SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    MetricsType slocMetric);

    public boolean hasFileResults(HashId hashId);

    public List<MetricsType> readFileResults(HashId hashId);

    public void storeDirectoryResults(HashId hashId, MetricsType slocResult);

    public boolean hasDirectoryResults(HashId hashId);

    public MetricsType readDirectoryResults(HashId hashId);

}
