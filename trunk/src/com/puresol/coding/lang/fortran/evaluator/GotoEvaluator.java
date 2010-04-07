package com.puresol.coding.lang.fortran.evaluator;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.coding.evaluator.gotos.AbstractGotoEvaluator;
import com.puresol.coding.evaluator.gotos.FoundGoto;
import com.puresol.coding.evaluator.gotos.FoundLabel;
import com.puresol.coding.lang.fortran.source.keywords.GotoKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LineLead;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;

public class GotoEvaluator extends AbstractGotoEvaluator {

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
	    if (token.getClass().equals(gotoKeyword)) {
		FoundGoto foundGoto = new FoundGoto(codeRange, pos, tokenStream
			.get(pos + 1).getText());
		addGoto(codeRange, foundGoto);
	    } else if (token.getClass().equals(LineLead.class)) {
		String label = token.getText().substring(1, 4).trim();
		if (!label.isEmpty()) {
		    FoundLabel foundLabel = new FoundLabel(codeRange, pos,
			    label);
		    addLabel(codeRange, foundLabel);
		}
	    }
	}
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

    @Override
    public String getProjectComment(ReportingFormat format)
	    throws UnsupportedReportingFormatException {
	String text = "";
	for (File file : getFiles()) {
	    if (getGotoNum(file) == 0) {
		continue;
	    }
	    text += file + "\n";
	    for (CodeRange codeRange : getCodeRanges(file)) {
		text += codeRange.getTitleString(format);
		for (FoundGoto foundGoto : getGotos(codeRange)) {
		    text += foundGoto.toString(format);
		}
	    }
	}
	return text;
    }

    @Override
    public String getFileComment(File file, ReportingFormat format)
	    throws UnsupportedReportingFormatException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getCodeRangeComment(CodeRange codeRange,
	    ReportingFormat format) throws UnsupportedReportingFormatException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public QualityLevel getProjectQuality() {
	if (getGotoNum() > getFiles().size() * 0.01) {
	    return QualityLevel.LOW;
	}
	if (getGotoNum() > 0) {
	    return QualityLevel.MEDIUM;
	}
	return QualityLevel.HIGH;
    }

    @Override
    public QualityLevel getQuality(File file) {
	for (CodeRange codeRange : getCodeRanges(file)) {
	    if (getGotos().get(codeRange).size() > 0) {
		return QualityLevel.LOW;
	    }
	}
	return QualityLevel.HIGH;
    }

    @Override
    public QualityLevel getQuality(CodeRange codeRange) {
	if (getGotos().get(codeRange).size() > 0) {
	    return QualityLevel.LOW;
	}
	return QualityLevel.HIGH;
    }
}
