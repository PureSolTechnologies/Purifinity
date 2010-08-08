package com.puresol.parser;

import java.io.Serializable;
import java.util.List;

import com.puresol.parser.tokens.TokenStream;

/**
 * This is the standard interface for any unspecialized parser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Parser extends Serializable {

	/**
	 * This method is called to scan the token stream. The actual parsing is
	 * done here.
	 * 
	 * @throws PartDoesNotMatchException
	 * @throws ParserException
	 */
	public void scan() throws PartDoesNotMatchException, ParserException;

	/**
	 * This method returns a name for the parser which symbolizes the grammar
	 * part which is investigated.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * This method returns the text wihtin the range of the current parser. If
	 * the parsing process is not finished, the returned test is from the start
	 * position until the current position.
	 * 
	 * @return
	 */
	public String getText();

	/**
	 * This method does the same like getText(), but only returns the for the
	 * parser visible parts.
	 * 
	 * @return
	 */
	public String getVisibleText();

	/**
	 * This method returns the current number of tokens within this parser
	 * range.
	 * 
	 * @return
	 */
	public int getTokenCount();

	/**
	 * This method returns the start position of the parser within the token
	 * stream.
	 * 
	 * @return
	 */
	public int getStartPosition();

	/**
	 * This method returns the current end position of the parser range.
	 * 
	 * @return
	 */
	public int getCurrentPosition();

	public int getEndPosition();

	/**
	 * This method returns the token stream investigated. This token stream is
	 * taken out of the token stream iterator. The Iterator itself is not to be
	 * returned outside for safety reasons.
	 * 
	 * The token stream can be used for look a head and look back functionality.
	 * 
	 * @return
	 */
	public TokenStream getTokenStream();

	public Parser getParentParser();

	public List<Parser> getChildParsers();

	public <T> List<T> getChildParsers(Class<T> parser);
}
