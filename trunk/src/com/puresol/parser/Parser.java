package com.puresol.parser;

import java.io.Serializable;
import java.util.List;

/**
 * This is the standard interface for any unspecialized parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Parser extends Serializable {

	public void scan() throws PartDoesNotMatchException, ParserException;

	public String getName();

	public String getText();

	public String getVisibleText();

	/**
	 * This method returns the current number of tokens within this parser
	 * range.
	 * 
	 * @return
	 */
	public int getTokenCount();

	public int getStartPosition();

	/**
	 * This method returns the current end position of the parser range.
	 * 
	 * @return
	 */
	public int getCurrentPosition();

	public int getEndPosition();

	public TokenStream getTokenStream();

	public Parser getParentParser();

	public List<Parser> getChildParsers();

	public <T> List<T> getChildParsers(Class<T> parser);
}
