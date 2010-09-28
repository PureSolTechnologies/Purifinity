package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.lexer.Token;

/**
 * This is the general interface for parser tables.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ParserTable extends Serializable {

	public ConcurrentMap<Construction, ParserActionSet> getPossibleActions(
			int currentState) throws GrammarException;

	/**
	 * This method returns the parse action for the current state id and the
	 * construction next in stream.
	 * 
	 * @param currentStateId
	 * @param construction
	 * @return
	 * @throws GrammarException
	 */
	public ParserAction getAction(int currentStateId, Construction construction)
			throws GrammarException;

	public ParserActionSet getActionSet(int currentState,
			Construction construction);

	public ParserActionSet getActionSet(int currentState,
			List<Construction> constructions);

	public Set<Construction> getActionTerminals();

	public Set<Construction> getGotoNonTerminals();

	/**
	 * This method returns all possible constructions in parser table which fit
	 * the token parameter.
	 * 
	 * @param token
	 * @return
	 * @throws GrammarException
	 */
	public List<Construction> getConstructionForToken(Token token)
			throws GrammarException;

}
