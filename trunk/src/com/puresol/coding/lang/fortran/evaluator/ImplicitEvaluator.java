package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.ArrayList;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class ImplicitEvaluator extends AbstractEvaluator {

    private static final Translator translator = Translator
	    .getTranslator(ImplicitEvaluator.class);

    public static final String NAME = "Implicit Evaluator";
    public static final String DESCRIPTION = translator
	    .i18n("Fortran supports the implicit declaration of variables which might lead to confusion. "
		    + "It's not a clean development method, too. "
		    + "Explicit declarations are obvious and should be used as the only method if possible.");
    public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

    public ImplicitEvaluator(ProjectAnalyser analyser) {
	super(analyser);
    }

    @Override
    public void run() {
	ProgressObserver observer = getMonitor();
	if (observer != null) {
	    observer.setDescription(NAME);
	    observer.setRange(0, getFiles().size());
	}
	// TODO Auto-generated method stub

	if (observer != null) {
	    observer.finish();
	}
    }

    @Override
    public String getName() {
	return NAME;
    }

    @Override
    public String getDescription(ReportingFormat format)
	    throws UnsupportedReportingFormatException {
	if (format == ReportingFormat.TEXT) {
	    return DESCRIPTION;
	} else if (format == ReportingFormat.HTML) {
	    return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
	}
	throw new UnsupportedReportingFormatException(format);
    }

    @Override
    public String getCodeRangeComment(CodeRange codeRange,
	    ReportingFormat format) throws UnsupportedReportingFormatException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getFileComment(File file, ReportingFormat format)
	    throws UnsupportedReportingFormatException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getProjectComment(ReportingFormat format)
	    throws UnsupportedReportingFormatException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public QualityLevel getProjectQuality() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public QualityLevel getQuality(File file) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public QualityLevel getQuality(CodeRange codeRange) {
	// TODO Auto-generated method stub
	return null;
    }

}
