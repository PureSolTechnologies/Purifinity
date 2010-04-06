package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.ArrayList;

import javax.i18n4j.Translator;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.evaluator.gotos.AbstractGotoEvaluator;
import com.puresol.coding.lang.fortran.source.keywords.GotoKeyword;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.utils.Property;

public class GotoEvaluator extends AbstractGotoEvaluator {

    private static final Translator translator = Translator
	    .getTranslator(GotoEvaluator.class);

    public static final String NAME = "Goto Evaluator";
    public static final String DESCRIPTION = translator
	    .i18n("The usage of GOTO is in most case inappropriate and therefore to be avoided."
		    + " This scanner checks the usage of GOTO and marks all usages.");
    public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

    public GotoEvaluator(ProjectAnalyser analyser) {
	super(analyser);
    }

    @Override
    public String getLanguage() {
	return "Fortran";
    }

    protected void analyse(CodeRange codeRange) {
	TokenStream tokenStream = codeRange.getTokenStream();
	GotoKeyword gotoKeyword = new GotoKeyword();
	for (int pos = codeRange.getStart(); pos <= codeRange.getStop(); pos++) {
	    Token token = tokenStream.get(pos);
	    if (gotoKeyword.included(token.getText())) {
		// TODO found GOTO! Process here!
	    }
	}
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
