package com.puresol.coding.evaluator.gotos;

import java.io.File;
import java.util.ArrayList;

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
