package com.puresol.coding.evaluator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.gui.progress.CallableProgressObservable;
import com.puresol.gui.progress.ProgressObserver;

/**
 * This class is used to export evaluation results to different file formats.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorASCIIExport implements
	CallableProgressObservable<Boolean> {

    private final String separator;
    private final File outputFile;
    private final ProjectAnalyzer analyzer;
    private ProgressObserver monitor;

    public EvaluatorASCIIExport(File outputFile, ProjectAnalyzer analyzer,
	    String separator) {
	super();
	this.outputFile = outputFile;
	this.analyzer = analyzer;
	this.separator = separator;
    }

    @Override
    public Boolean call() throws IOException {
	FileWriter writer = new FileWriter(outputFile);
	try {
	    boolean first = true;
	    List<AnalyzedFile> analyzedFiles = analyzer.getAnalyzedFiles();
	    if (monitor != null) {
		monitor.setRange(0, analyzedFiles.size() - 1);
		monitor.setTitle("Exporting Values in TSV format...");
	    }
	    List<CodeRangeEvaluatorFactory> evaluators = CodeRangeEvaluatorManager
		    .getAll();
	    for (int id = 0; id < analyzedFiles.size(); id++) {
		AnalyzedFile analyzedFile = analyzedFiles.get(id);
		if (monitor != null) {
		    monitor.setText(analyzedFile.getFile().getPath());
		    monitor.setStatus(id);
		}
		Analysis analysis = analyzer.getAnalysis(analyzedFile);
		for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
		    if (first) {
			first = false;
			writer.write("File");
			writer.write(separator);
			writer.write("CodeRangeType");
			writer.write(separator);
			writer.write("Name");
			for (CodeRangeEvaluatorFactory evaluatorFactory : evaluators) {
			    CodeRangeEvaluator evaluator = evaluatorFactory
				    .create(analysis.getLanguage(), codeRange);
			    evaluator.run();
			    for (Result result : evaluator.getResults()) {
				writer.write(separator);
				writer.write(result.getName());
			    }
			}
			writer.write('\n');
		    }
		    writer.write(analyzedFile.getSourceFile().getPath());
		    writer.write(separator);
		    writer.write(codeRange.getType().getName());
		    writer.write(separator);
		    writer.write(codeRange.getName());
		    for (CodeRangeEvaluatorFactory evaluatorFactory : evaluators) {
			CodeRangeEvaluator evaluator = evaluatorFactory.create(
				analysis.getLanguage(), codeRange);
			evaluator.run();
			for (Result result : evaluator.getResults()) {
			    writer.write(separator);
			    writer.write(String.valueOf(result.getValue()));
			}
		    }
		    writer.write('\n');
		}
	    }
	} finally {
	    writer.close();
	    if (monitor != null) {
		monitor.finished(this);
	    }
	}
	return true;
    }

    @Override
    public void setMonitor(ProgressObserver observer) {
	monitor = observer;
    }

    @Override
    public ProgressObserver getMonitor() {
	return monitor;
    }
}
