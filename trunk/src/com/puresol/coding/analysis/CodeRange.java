package com.puresol.coding.analysis;

import java.io.File;
import java.io.Serializable;
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

    /**
     * This method returns the file name from which the code range was
     * extracted.
     * 
     * @return A File object containing the file name and path is returned.
     */
    public File getFile();

    /**
     * This method is used to return the programming language which is used
     * within the code range.
     * 
     * @return A reference to a ProgrammingLanguage object is returned
     *         representing the programming language used.
     */
    public ProgrammingLanguage getLanguage();

    /**
     * This method returns the code range type, if it is of importance for
     * analysis like method declarations, class declarations, subprograms and
     * others. If it's not of special importance just CodeRangeType.FRAGMENT is
     * returned.
     * 
     * @return A constant of enum CodeRangeType is returned.
     */
    public CodeRangeType getCodeRangeType();

    /**
     * This method returns the text of the code range.
     * 
     * @return A String object is returned containing the text of the code
     *         range.
     */
    public String getText();

    /**
     * This method returns the token stream where the code range was extracted
     * from.
     * 
     * @return A TokenStream object is returned.
     */
    public TokenStream getTokenStream();

    /**
     * This method returns all tokens of the code range wihtin a List of Token.
     * 
     * @return A List<Token> object is returned containing all tokens of the
     *         code range.
     */
    public List<Token> getTokens();

    /**
     * This method returns the id of the first token of the code range.
     * 
     * @return An int is returned containing the id of the first token of this
     *         code range.
     */
    public int getStartId();

    /**
     * This method returns the id of the last token of the code range.
     * 
     * @return An int is returned containing the id of the last token of this
     *         code range.
     */
    public int getStopId();

    /**
     * This method returns the line number of the first token and therefore the
     * starting line of the code range.
     * 
     * @return An int is returned containing the start line of the code range.
     */
    public int getStartLine();

    /**
     * This method returns the line number of the last token and therefore the
     * end line of the code range.
     * 
     * @return An int is returned containing the end line of the code range.
     */
    public int getStopLine();

    /**
     * This method returns all child parser in List<Parser> directly casted to a
     * List<CodeRange>. This makes access to cub code ranges easier and more
     * clean.
     * 
     * @return A List<CodeRange> object is returned containing all sub code
     *         ranges.
     */
    public List<CodeRange> getChildCodeRanges();

    /**
     * This method returns all sub code ranges which are of type codeRange.
     * 
     * @param <T>
     *            is the class of which the child code ranges should be.
     * @param codeRange
     *            is the class object of the child code range type.
     * @return A List<T> is returned containing the filtered child code ranges.
     */
    public <T> List<T> getChildCodeRanges(Class<T> codeRange);

    /**
     * This method returns the parent code range of the current code range.
     * 
     * @return A CodeRange object is returned representing the parent code
     *         range.
     */
    public CodeRange getParentCodeRange();

    /**
     * This method adds a new child code range to the list of children.
     * 
     * @param codeRange
     *            is the code range to add.
     */
    public void addChildCodeRange(CodeRange codeRange);

    /**
     * This method returns a title for the code range in the specified format.
     * 
     * @param format
     *            is the format to create as output.
     * @return A String is created as title string.
     * @throws UnsupportedReportingFormatException
     *             is thrown in case of a reporting format which is not
     *             implemented.
     */
    public String getTitleString(ReportingFormat format)
	    throws UnsupportedReportingFormatException;

    /**
     * This method returns the content of the code range in a specified format
     * for reporting purposes.
     * 
     * @param format
     *            is the format to create as output.
     * @return A String is created as title string.
     * @throws UnsupportedReportingFormatException
     *             is thrown in case of a reporting format which is not
     *             implemented.
     */
    public String getReportingString(ReportingFormat format)
	    throws UnsupportedReportingFormatException;

    /**
     * This method creates a new code range with same attributes, but with a sub
     * range of tokens. This is used in evaluation tools to store and later
     * process critical areas.
     * 
     * @param New
     *            start id for the partial code range.
     * @param New
     *            stop id for the partial code range.
     * @return A new object of CodeRange is returned with the new range of
     *         tokens.
     */
    public CodeRange createPartialCodeRange(int newStart, int newStop);

    /**
     * Standard compareTo method for CodeRange.
     */
    @Override
    public int compareTo(CodeRange other);

}
