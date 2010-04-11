package com.puresol.coding.evaluator.gotos;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

abstract public class AbstractGotoEvaluator extends AbstractEvaluator {

	private static final Translator translator = Translator
			.getTranslator(AbstractGotoEvaluator.class);

	public static final String NAME = "Goto Evaluator";
	public static final String DESCRIPTION = translator
			.i18n("The usage of GOTO is in most case inappropriate and therefore to be avoided."
					+ " This scanner checks the usage of GOTO and marks all usages.");
	public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

	private final Hashtable<CodeRange, ArrayList<FoundGoto>> gotos = new Hashtable<CodeRange, ArrayList<FoundGoto>>();
	private final Hashtable<CodeRange, ArrayList<FoundLabel>> labels = new Hashtable<CodeRange, ArrayList<FoundLabel>>();

	public AbstractGotoEvaluator(ProjectAnalyser analyser) {
		super(analyser);
	}

	abstract public String getLanguage();

	@Override
	public final void run() {
		ProgressObserver observer = getMonitor();
		if (observer != null) {
			observer.setDescription(NAME);
			observer.setRange(0, getProjectAnalyser().getFiles().size());
			observer.setStatus(0);
			observer.showProgressPercent();
		}
		int count = 0;
		for (File file : getProjectAnalyser().getFiles()) {
			if (Thread.interrupted()) {
				return;
			}
			count++;
			if (observer != null) {
				observer.setStatus(count);
			}
			for (CodeRange codeRange : getProjectAnalyser().getCodeRanges(file)) {
				if (Thread.interrupted()) {
					return;
				}
				if (!codeRange.getLanguage().equals(getLanguage())) {
					continue;
				}
				if (!codeRange.getType().isRunnableCodeSegment()) {
					continue;
				}
				if (!getFiles().contains(file)) {
					addFile(file);
				}
				addCodeRange(codeRange);
				analyse(codeRange);
			}
		}
		if (observer != null) {
			observer.finish();
		}
	}

	abstract protected void analyse(CodeRange codeRange);

	protected final void addGoto(CodeRange codeRange, FoundGoto foundGoto) {
		if (!gotos.containsKey(codeRange)) {
			gotos.put(codeRange, new ArrayList<FoundGoto>());
		}
		gotos.get(codeRange).add(foundGoto);
		ArrayList<FoundLabel> foundLabels = labels.get(codeRange);
		if (foundLabels == null) {
			return;
		}
		for (FoundLabel label : foundLabels) {
			if (label.getLabelName().equals(foundGoto.getLabelName())) {
				label.addGoto(foundGoto);
			}
		}
	}

	public final Hashtable<CodeRange, ArrayList<FoundGoto>> getGotos() {
		return gotos;
	}

	public final ArrayList<FoundGoto> getGotos(CodeRange codeRange) {
		return gotos.get(codeRange);
	}

	public int getGotoNum() {
		int count = 0;
		for (File file : getFiles()) {
			count += getGotoNum(file);
		}
		return count;
	}

	public int getGotoNum(File file) {
		int count = 0;
		for (CodeRange codeRange : getCodeRanges(file)) {
			count += getGotoNum(codeRange);
		}
		return count;
	}

	public int getGotoNum(CodeRange codeRange) {
		ArrayList<FoundGoto> gotos = getGotos().get(codeRange);
		if (gotos == null) {
			return 0;
		}
		return gotos.size();
	}

	protected final void addLabel(CodeRange codeRange, FoundLabel foundLabel) {
		if (!labels.containsKey(codeRange)) {
			labels.put(codeRange, new ArrayList<FoundLabel>());
		}
		labels.get(codeRange).add(foundLabel);
		ArrayList<FoundGoto> foundGotos = gotos.get(codeRange);
		if (foundGotos == null) {
			return;
		}
		for (FoundGoto foundGoto : foundGotos) {
			if (foundGoto.getLabelName().equals(foundLabel.getLabelName())) {
				foundLabel.addGoto(foundGoto);
			}
		}
	}

	public final Hashtable<CodeRange, ArrayList<FoundLabel>> getLabels() {
		return labels;
	}

	public final ArrayList<FoundLabel> getLabels(CodeRange codeRange) {
		return labels.get(codeRange);
	}

	public int getLabelNum() {
		int count = 0;
		for (File file : getFiles()) {
			count += getLabelNum(file);
		}
		return count;
	}

	public int getLabelNum(File file) {
		int count = 0;
		for (CodeRange codeRange : getCodeRanges(file)) {
			count += getLabelNum(codeRange);
		}
		return count;
	}

	public int getLabelNum(CodeRange codeRange) {
		ArrayList<FoundLabel> labels = getLabels().get(codeRange);
		if (labels == null) {
			return 0;
		}
		return labels.size();
	}

	@Override
	public final String getName() {
		return NAME;
	}

	@Override
	public final String getDescription(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return DESCRIPTION;
		} else if (format == ReportingFormat.HTML) {
			return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
		}
		throw new UnsupportedReportingFormatException(format);
	}
}
