package com.puresol.coding.metrics.halstead;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.analysis.api.CodeRange;
import com.puresol.coding.analysis.api.FileAnalysis;
import com.puresol.coding.analysis.api.FileStore;
import com.puresol.coding.analysis.api.FileStoreFactory;
import com.puresol.coding.analysis.api.ProgrammingLanguage;
import com.puresol.coding.metrics.AbstractProjectMetric;
import com.puresol.coding.quality.QualityCharacteristic;
import com.puresol.coding.quality.SourceCodeQuality;

public class ProjectHalsteadMetric extends
	AbstractProjectMetric<HalsteadMetric> {

    private static final long serialVersionUID = -5093217611195212999L;

    private final FileStore fileStore = FileStoreFactory.getInstance();

    public ProjectHalsteadMetric(AnalysisRun projectAnalyzer) {
	super(projectAnalyzer);
    }

    @Override
    protected Map<String, SourceCodeQuality> processFile(AnalyzedFile file)
	    throws IOException {
	Map<String, SourceCodeQuality> results = new HashMap<String, SourceCodeQuality>();
	FileAnalysis analysis = fileStore.loadAnalysis(file.getHashId());
	ProgrammingLanguage language = analysis.getLanguage();

	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    HalsteadMetric metric = new HalsteadMetric(language, codeRange);
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
	return HalsteadMetric.DESCRIPTION;
    }

    @Override
    public List<QualityCharacteristic> getEvaluatedQualityCharacteristics() {
	return HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS;
    }
}
