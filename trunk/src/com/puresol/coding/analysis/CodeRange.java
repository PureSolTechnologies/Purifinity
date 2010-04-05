package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
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
public interface CodeRange extends Comparable<CodeRange>, Cloneable {

	public File getFile();

	public String getLanguage();

	public String getTypeName();

	public CodeRangeType getType();

	public String getName();

	public String getText();

	public TokenStream getTokenStream();

	public ArrayList<Token> getTokens();

	public int getStart();

	public int getStop();

	public String getTitleString(ReportingFormat format)
			throws UnsupportedReportingFormatException;

	public String toString(ReportingFormat format)
			throws UnsupportedReportingFormatException;

	public CodeRange createPartialCodeRange(int newStart, int newStop);

	@Override
	public int compareTo(CodeRange other);
}
