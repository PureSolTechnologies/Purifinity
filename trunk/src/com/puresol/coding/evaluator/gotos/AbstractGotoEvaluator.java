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
			observer.setRange(0, getFiles().size());
		}
		int count = 0;
		for (File file : getProjectAnalyser().getFiles()) {
			for (CodeRange codeRange : getProjectAnalyser().getCodeRanges(file)) {
				count++;
				if (!codeRange.getLanguage().equals(getLanguage())) {
					continue;
				}
				if (observer != null) {
					observer.setStatus(count);
				}
				addFile(file);
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
		if (gotos.containsKey(codeRange)) {
			gotos.put(codeRange, new ArrayList<FoundGoto>());
		}
		gotos.get(codeRange).add(foundGoto);
	}

	public final Hashtable<CodeRange, ArrayList<FoundGoto>> getGotos() {
		return gotos;
	}

	protected final void addLabel(CodeRange codeRange, FoundLabel foundLabel) {
		if (labels.containsKey(codeRange)) {
			labels.put(codeRange, new ArrayList<FoundLabel>());
		}
		labels.get(codeRange).add(foundLabel);
	}

	public final Hashtable<CodeRange, ArrayList<FoundLabel>> getLabels() {
		return labels;
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
