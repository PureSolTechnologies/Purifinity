package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Terminal;

/**
 * This is the general interface for parser tables.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ParserTable extends Serializable {

	public Map<Construction, ParserActionSet> getPossibleActions(
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

	public Set<Terminal> getActionTerminals();

	public Set<NonTerminal> getGotoNonTerminals();

	public int getStateCount();
}
