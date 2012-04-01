package com.puresol.coding.metrics.codedepth;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

public class ProjectCodeDepthMetric extends
	AbstractProjectMetric<CodeDepthMetric> {

    private static final long serialVersionUID = -5093217611195212999L;

    public ProjectCodeDepthMetric(ProjectAnalyzer projectAnalyzer) {
	super(projectAnalyzer);
    }

    @Override
    protected Map<String, SourceCodeQuality> processFile(AnalyzedFile file)
	    throws IOException {
	Map<String, SourceCodeQuality> results = new HashMap<String, SourceCodeQuality>();
	Analysis analysis = getProjectAnalyzer().getAnalysis(file);
	ProgrammingLanguage language = analysis.getLanguage();
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    CodeDepthMetric metric = new CodeDepthMetric(language, codeRange);
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
	return CodeDepthMetric.DESCRIPTION;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }

}
