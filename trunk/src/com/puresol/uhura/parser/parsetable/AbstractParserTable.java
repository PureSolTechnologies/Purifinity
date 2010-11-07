package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Terminal;

/**
 * This class is the abstract implementation of a parser table. The table based
 * parsers use this tables for parsing texts very efficiently.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractParserTable implements ParserTable {

	private static final long serialVersionUID = 4097931723838051724L;

	private static final Logger logger = Logger
			.getLogger(AbstractParserTable.class);

	private final List<Map<Construction, ParserActionSet>> table = new ArrayList<Map<Construction, ParserActionSet>>();
	private final Set<Terminal> actionTerminals = new LinkedHashSet<Terminal>();
	private final Set<NonTerminal> gotoNonTerminals = new LinkedHashSet<NonTerminal>();

	private final Grammar grammar;

	public AbstractParserTable(Grammar grammar) throws GrammarException {
		super();
		this.grammar = grammar;
		logger.trace("Calculate parser table...");
		calculate();
		logger.trace("done.");
	}

	protected abstract void calculate() throws GrammarException;

	protected void addActionTerminal(Terminal construction) {
		actionTerminals.add(construction);
	}

	protected void addGotoNonTerminal(NonTerminal construction) {
		gotoNonTerminals.add(construction);
	}

	/**
	 * @return the actionTerminals
	 */
	@Override
	public final Set<Terminal> getActionTerminals() {
		return actionTerminals;
	}

	/**
	 * @return the gotoNonTerminals
	 */
	@Override
	public final Set<NonTerminal> getGotoNonTerminals() {
		return gotoNonTerminals;
	}

	protected final void addAction(int stateId, Construction construction,
			ParserAction action) {
		while (table.size() <= stateId) {
			table.add(new HashMap<Construction, ParserActionSet>());
		}
		ParserActionSet actionSet = table.get(stateId).get(construction);
		if (actionSet == null) {
			actionSet = new ParserActionSet();
			table.get(stateId).put(construction, actionSet);
		}
		actionSet.addAction(action);
	}

	public final Grammar getGrammar() {
		return grammar;
	}

	@Override
	public final Map<Construction, ParserActionSet> getPossibleActions(
			int currentState) throws GrammarException {
		return table.get(currentState);
	}

	@Override
	public final ParserAction getAction(int currentState,
			Construction construction) throws GrammarException {
		if (construction == null) {
			return new ParserAction(ActionType.ERROR, -1);
		}
		Map<Construction, ParserActionSet> actions = table.get(currentState);
		if (actions == null) {
			return new ParserAction(ActionType.ERROR, -1);
		}
		ParserActionSet action = actions.get(construction);
		if (action == null) {
			return new ParserAction(ActionType.ERROR, -1);
		}
		return action.getAction();
	}

	@Override
	public final ParserActionSet getActionSet(int currentState,
			Construction construction) {
		if (construction == null) {
			return ParserActionSet.getErrorSet();
		}
		Map<Construction, ParserActionSet> actions = table.get(currentState);
		if (actions == null) {
			return ParserActionSet.getErrorSet();
		}
		ParserActionSet set = actions.get(construction);
		if (set == null) {
			return ParserActionSet.getErrorSet();
		}
		return set;
	}

	@Override
	public final String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("=============\n");
		buffer.append("Parsing Table\n");
		buffer.append("=============\n");
		buffer.append("\n");
		buffer.append(toColumn("STATE:|"));
		boolean first = true;
		for (int i = 0; i < actionTerminals.size(); i++) {
			if (first) {
				buffer.append(toColumn("ACTION:"));
				first = false;
			} else {
				buffer.append(toColumn(" "));
			}
		}
		buffer.append(toColumn("|"));
		first = true;
		for (int i = 0; i < gotoNonTerminals.size(); i++) {
			if (first) {
				buffer.append(toColumn("GOTO:"));
				first = false;
			} else {
				buffer.append(toColumn(" "));
			}
		}
		buffer.append("\n");
		buffer.append(toColumn("|"));
		for (Construction construction : actionTerminals) {
			buffer.append(toColumn(construction.getName()));
		}
		buffer.append(toColumn("|"));
		for (Construction construction : gotoNonTerminals) {
			buffer.append(toColumn(construction.getName()));
		}
		buffer.append("\n");
		buffer.append(toColumn("------|"));
		for (int i = 0; i < actionTerminals.size(); i++) {
			buffer.append(toColumn("-------"));
		}
		buffer.append(toColumn("------|"));
		for (int i = 0; i < gotoNonTerminals.size(); i++) {
			buffer.append(toColumn("-------"));
		}
		buffer.append("\n");
		for (int state = 0; state < table.size(); state++) {
			buffer.append(toColumn(String.valueOf(state) + " |"));
			for (Construction construction : actionTerminals) {
				buffer.append(toColumn(getActionSet(state, construction)
						.toString()));
			}
			buffer.append(toColumn("|"));
			for (Construction construction : gotoNonTerminals) {
				buffer.append(toColumn(getActionSet(state, construction)
						.toString()));
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	private String toColumn(String s) {
		StringBuffer result = new StringBuffer();
		while (result.length() + s.length() < 7) {
			result.append(' ');
		}
		result.append(' ');
		result.append(s);
		return result.toString();
	}
}
