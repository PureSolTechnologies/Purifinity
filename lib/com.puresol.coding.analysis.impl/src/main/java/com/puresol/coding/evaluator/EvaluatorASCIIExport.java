package com.puresol.coding.evaluator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.puresol.coding.analysis.Activator;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.analysis.api.Analysis;
import com.puresol.coding.analysis.api.CodeRange;

/**
 * This class is used to export evaluation results to different file formats.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EvaluatorASCIIExport extends Job {

    private final String separator;
    private final File outputFile;
    private final ProjectAnalyzer analyzer;

    public EvaluatorASCIIExport(File outputFile, ProjectAnalyzer analyzer,
	    String separator) {
	super("Evaluator ASCII Export");
	this.outputFile = outputFile;
	this.analyzer = analyzer;
	this.separator = separator;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
	try {
	    FileWriter writer = new FileWriter(outputFile);
	    try {
		boolean first = true;
		List<AnalyzedFile> analyzedFiles = analyzer.getAnalyzedFiles();
		monitor.beginTask("Exporting Values in TSV format...",
			analyzedFiles.size() - 1);
		List<CodeRangeEvaluatorFactory> evaluators = CodeRangeEvaluatorManager
			.getAll();
		for (int id = 0; id < analyzedFiles.size(); id++) {
		    AnalyzedFile analyzedFile = analyzedFiles.get(id);
		    monitor.worked(id);
		    Analysis analysis = analyzer.getAnalysis(analyzedFile);
		    for (CodeRange codeRange : analysis
			    .getAnalyzableCodeRanges()) {
			if (first) {
			    first = false;
			    writer.write("File");
			    writer.write(separator);
			    writer.write("CodeRangeType");
			    writer.write(separator);
			    writer.write("Name");
			    for (CodeRangeEvaluatorFactory evaluatorFactory : evaluators) {
				CodeRangeEvaluator evaluator = evaluatorFactory
					.create(analysis.getLanguage(),
						codeRange);
				evaluator.schedule();
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
			    CodeRangeEvaluator evaluator = evaluatorFactory
				    .create(analysis.getLanguage(), codeRange);
			    evaluator.schedule();
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
	    }
	} catch (IOException e) {
	    monitor.done();
	    return new Status(Status.ERROR, Activator.getBundleContext().getBundle()
		    .getSymbolicName(), e.getMessage());
	}
	monitor.done();
	return Status.OK_STATUS;
    }
}
