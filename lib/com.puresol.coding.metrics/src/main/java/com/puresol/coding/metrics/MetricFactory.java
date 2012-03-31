package com.puresol.coding.metrics;

import java.io.File;
import java.util.List;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.quality.QualityCharacteristic;

/**
 * This is the base interface for all metric factories. These factories are used
 * to create, read and update metrics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface MetricFactory {

    /**
     * This method returns the name of the metric which is created by the
     * factory.
     * 
     * @return A string with the name is returned.
     */
    public String getName();

    /**
     * This method returns a description about the metric created by this
     * factory.
     * 
     * @return A string with the description is returned.
     */
    public String getDescription();

    /**
     * This method returns a list of quality characteristics which are tackled
     * by the metric created by this factory.
     * 
     * @return A List of {@link QualityCharacteristic} is returned.
     */
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics();

    /**
     * This method creates a ProjectMetric for the provided project analyzer.
     * 
     * @param projectAnalyzer
     * @return A project metric object is returned.
     */
    public ProjectMetric createProjectMetric(ProjectAnalyzer projectAnalyzer);

    /**
     * This method creates a DirectoryMetric for the provided project analyzer
     * and the directory.
     * 
     * @param projectAnalyzer
     * @param directory
     *            is the relative path of the directory within the source
     *            directory and workspace directory.
     * @return A directory metric object is returned.
     */
    public FileMetric createDirectoryMetric(ProjectAnalyzer projectAnalyzer,
	    File directory);

    /**
     * This method creates a FileMetric for the provided project analyzer and
     * the file.
     * 
     * @param projectAnalyzer
     * @param file
     *            is the relative path of the file within the source directory
     *            and workspace directory.
     * @return A file metric object is returned.
     */
    public FileMetric createFileMetric(ProjectAnalyzer projectAnalyzer,
	    AnalyzedFile file);
}
