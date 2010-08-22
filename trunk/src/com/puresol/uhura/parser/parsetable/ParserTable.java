package com.puresol.uhura.parser.parsetable;

import java.util.List;

import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.lexer.Token;

/**
 * This is the general interface for parser tables.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ParserTable {

	/**
	 * This method returns the parse action for the current state id and the
	 * construction next in stream.
	 * 
	 * @param currentStateId
	 * @param construction
	 * @return
	 */
	public ParserAction getAction(int currentStateId, Construction construction);

	public List<Construction> getActionTerminals();

	public List<Construction> getGotoNonTerminals();

	public Construction getConstructionForToken(Token token);

}
