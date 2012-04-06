package com.puresol.coding.metrics.sloc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

public class ProjectSLOCMetric extends AbstractProjectMetric<SLOCMetric> {

    private static final long serialVersionUID = -5093217611195212999L;

    public ProjectSLOCMetric(AnalysisRun projectAnalyzer) {
	super(projectAnalyzer);
    }

    @Override
    protected Map<String, SourceCodeQuality> processFile(AnalyzedFile file)
	    throws IOException {
	Map<String, SourceCodeQuality> results = new HashMap<String, SourceCodeQuality>();
	FileAnalysis analysis = getProjectAnalyzer().getAnalysis(file);
	ProgrammingLanguage language = analysis.getLanguage();

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    SLOCMetric metric = new SLOCMetric(language, codeRange);
	    metric.schedule();
	    results.put(
		    file.getFile().getPath() + ": "
			    + codeRange.getType().getName() + " '"
			    + codeRange.getName() + "'", metric.getQuality());
	}
	return results;
    }

    @Override
    public String getDescription() {
	return SLOCMetric.DESCRIPTION;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return SLOCMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }
}
