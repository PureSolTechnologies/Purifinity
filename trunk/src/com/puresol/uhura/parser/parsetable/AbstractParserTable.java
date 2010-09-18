package com.puresol.uhura.parser.parsetable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.ConstructionType;
import com.puresol.uhura.lexer.Token;

public abstract class AbstractParserTable implements ParserTable {

	private static final long serialVersionUID = 4097931723838051724L;

	private static final Logger logger = Logger
			.getLogger(AbstractParserTable.class);

	private final List<ConcurrentMap<Construction, ParserActionSet>> table = new ArrayList<ConcurrentMap<Construction, ParserActionSet>>();
	private final List<Construction> actionTerminals = new ArrayList<Construction>();
	private final List<Construction> gotoNonTerminals = new ArrayList<Construction>();

	private final Grammar grammar;
	private final boolean ignoreCase;

	public AbstractParserTable(Grammar grammar) throws GrammarException {
		super();
		this.grammar = grammar;
		ignoreCase = grammar.isIgnoreCase();
		logger.trace("Calculate parser table...");
		calculate();
		logger.trace("done.");
	}

	protected abstract void calculate() throws GrammarException;

	protected void addActionTerminal(Construction construction) {
		actionTerminals.add(construction);
	}

	protected void addGotoNonTerminal(Construction construction) {
		gotoNonTerminals.add(construction);
	}

	/**
	 * @return the actionTerminals
	 */
	@Override
	public final List<Construction> getActionTerminals() {
		return actionTerminals;
	}

	/**
	 * @return the gotoNonTerminals
	 */
	@Override
	public final List<Construction> getGotoNonTerminals() {
		return gotoNonTerminals;
	}

	protected final void addAction(int stateId, Construction construction,
			ParserAction action) {
		while (table.size() <= stateId) {
			table.add(new ConcurrentHashMap<Construction, ParserActionSet>());
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
	public List<Construction> getConstructionForToken(Token token)
			throws GrammarException {
		List<Construction> constructions = new ArrayList<Construction>();
		for (Construction construction : actionTerminals) {
			if (construction.getName().equals(token.getName())) {
				String constructionText = construction.getText();
				String tokenText = token.getText();
				if (ignoreCase) {
					constructionText = constructionText.toUpperCase();
					tokenText = tokenText.toUpperCase();
				}
				if (constructionText.isEmpty()
						|| (constructionText.equals(tokenText))) {
					constructions.add(construction);
				}
			}
		}
		return constructions;
	}

	@Override
	public final ConcurrentMap<Construction, ParserActionSet> getPossibleActions(
			int currentState) throws GrammarException {
		return table.get(currentState);
	}

	@Override
	public final ParserAction getAction(int currentState,
			Construction construction) throws GrammarException {
		if (construction == null) {
			return new ParserAction(ActionType.ERROR, -1);
		}
		ConcurrentMap<Construction, ParserActionSet> actions = table
				.get(currentState);
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
		ConcurrentMap<Construction, ParserActionSet> actions = table
				.get(currentState);
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
	public final ParserActionSet getActionSet(int currentState,
			List<Construction> constructions) {
		if ((constructions == null) || (constructions.size() == 0)) {
			return ParserActionSet.getErrorSet();
		}
		ParserActionSet actionSet = new ParserActionSet();
		for (Construction construction : constructions) {
			actionSet.addActions(getActionSet(currentState, construction));
		}
		return actionSet;
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
			if (construction.getType() == ConstructionType.TERMINAL) {
				buffer.append(toColumn(construction.getName()));
			} else {
				buffer.append(toColumn(construction.getText()));
			}
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
