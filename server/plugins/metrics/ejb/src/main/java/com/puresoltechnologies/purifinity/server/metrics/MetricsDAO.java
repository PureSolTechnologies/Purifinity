package com.puresoltechnologies.purifinity.server.metrics;

import java.util.List;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;

public interface MetricsDAO<FileMetricsType, DirectoryMetricsType> {

    public void storeFileResults(HashId hashId, SourceCodeLocation sourceCodeLocation, CodeRange codeRange,
	    FileMetricsType slocMetric) throws EvaluationStoreException;

    public boolean hasFileResults(HashId hashId) throws EvaluationStoreException;

    public List<FileMetricsType> readFileResults(HashId hashId) throws EvaluationStoreException;

    public void storeDirectoryResults(HashId hashId, DirectoryMetricsType slocResult) throws EvaluationStoreException;

    public boolean hasDirectoryResults(HashId hashId) throws EvaluationStoreException;

    public DirectoryMetricsType readDirectoryResults(HashId hashId) throws EvaluationStoreException;

}
