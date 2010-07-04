package com.puresol.coding.evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyzer;

/**
 * This is the base implementation of an abstract Evaluator. The implementation
 * contains the management of files and coderanges and the handling of the
 * project analyser.
 * 
 * The handling of an external observer is also included to show progress bars,
 * especially for operations taking some time.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractEvaluator implements Evaluator {

	private static final long serialVersionUID = 1296590575296210481L;

	private final ProjectAnalyzer analyzer;
	private final List<File> files = new ArrayList<File>();
	private final Map<File, List<CodeRange>> codeRanges = new Hashtable<File, List<CodeRange>>();

	transient private ProgressObserver observer = null;

	public AbstractEvaluator(ProjectAnalyzer analyser) {
		this.analyzer = analyser;
	}

	@Override
	public final ProjectAnalyzer getProjectAnalyser() {
		return analyzer;
	}

	protected final void addFile(File file) {
		files.add(file);
	}

	@Override
	public final List<File> getFiles() {
		return files;
	}

	protected final void addCodeRange(CodeRange codeRange) {
		if (!codeRanges.containsKey(codeRange.getFile())) {
			codeRanges.put(codeRange.getFile(), new ArrayList<CodeRange>());
		}
		codeRanges.get(codeRange.getFile()).add(codeRange);
	}

	@Override
	public final List<CodeRange> getCodeRanges(File file) {
		List<CodeRange> ranges = codeRanges.get(file);
		if (ranges == null) {
			return new ArrayList<CodeRange>();
		}
		return ranges;
	}

	@Override
	public final void setMonitor(ProgressObserver observer) {
		this.observer = observer;
	}

	protected final ProgressObserver getMonitor() {
		return observer;
	}

	protected final List<CodeRange> getEvaluableCodeRanges(File file) {
		List<CodeRange> ranges = new ArrayList<CodeRange>();
		getEvaluableCodeRanges(ranges, analyzer.getAnalyzer(file)
				.getRootCodeRange());
		return ranges;
	}

	private final void getEvaluableCodeRanges(List<CodeRange> ranges,
			CodeRange current) {
		if (current == null) {
			return;
		}
		if (current.getCodeRangeType().isEvaluable()) {
			ranges.add(current);
		}
		for (CodeRange child : current.getChildCodeRanges()) {
			getEvaluableCodeRanges(ranges, child);
		}
	}
}
