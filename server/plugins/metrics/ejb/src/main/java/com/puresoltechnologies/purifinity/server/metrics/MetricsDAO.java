package com.puresoltechnologies.purifinity.server.metrics;

import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;

public interface MetricsDAO<FileMetricsType, DirectoryMetricsType> {

    public void storeFileResults(HashId hashId,
	    SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    FileMetricsType slocMetric);

    public boolean hasFileResults(HashId hashId);

    public List<FileMetricsType> readFileResults(HashId hashId);

    public void storeDirectoryResults(HashId hashId,
	    DirectoryMetricsType slocResult);

    public boolean hasDirectoryResults(HashId hashId);

    public DirectoryMetricsType readDirectoryResults(HashId hashId);

}
