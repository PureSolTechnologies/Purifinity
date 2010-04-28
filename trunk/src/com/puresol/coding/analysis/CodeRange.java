package com.puresol.coding.analysis;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.parser.Parser;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;

/**
 * This is a general interface for all kinds of code ranges. It's used for
 * general code range handling.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface CodeRange extends Parser, Serializable, Comparable<CodeRange>,
	Cloneable {

    public File getFile();

    public ProgrammingLanguage getLanguage();

    public CodeRangeType getCodeRangeType();

    public String getText();

    public TokenStream getTokenStream();

    public ArrayList<Token> getTokens();

    public int getStartId();

    public int getStopId();

    public int getStartLine();

    public int getStopLine();

    public List<CodeRange> getChildCodeRanges();

    public <T> List<T> getChildCodeRanges(Class<T> codeRange);

    public CodeRange getParentCodeRange();

    public void addCodeRange(CodeRange codeRange);

    public String getTitleString(ReportingFormat format)
	    throws UnsupportedReportingFormatException;

    public String toString(ReportingFormat format)
	    throws UnsupportedReportingFormatException;

    public CodeRange createPartialCodeRange(int newStart, int newStop);

    @Override
    public int compareTo(CodeRange other);

}
