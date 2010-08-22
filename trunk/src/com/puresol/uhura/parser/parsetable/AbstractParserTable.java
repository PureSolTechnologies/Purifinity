package com.puresol.uhura.parser.parsetable;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.ConstructionType;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.parser.ParserException;

public abstract class AbstractParserTable implements ParserTable {

	private final List<ConcurrentMap<Construction, ParserAction>> table = new CopyOnWriteArrayList<ConcurrentMap<Construction, ParserAction>>();
	private final List<Construction> actionTerminals = new CopyOnWriteArrayList<Construction>();
	private final List<Construction> gotoNonTerminals = new CopyOnWriteArrayList<Construction>();

	private final Grammar grammar;

	public AbstractParserTable(Grammar grammar) throws ParserException {
		super();
		this.grammar = grammar;
		calculate();
	}

	protected abstract void calculate() throws ParserException;

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
			ParserAction action) throws ParserException {
		while (table.size() <= stateId) {
			table.add(new ConcurrentHashMap<Construction, ParserAction>());
		}
		ParserAction already = table.get(stateId).get(construction);
		if (already != null) {
			if (!action.equals(already)) {
				String text = "Invalid grammar! Conflict in state id: "
						+ stateId + "\n";
				text += "construction:     " + construction + "\n";
				text += "in stack:         " + already.toString() + "\n";
				text += "to be introduced: " + action.toString();
				throw new ParserException(text);
			}
		}
		table.get(stateId).put(construction, action);
	}

	public Grammar getGrammar() {
		return grammar;
	}

	@Override
	public Construction getConstructionForToken(Token token) {
		for (Construction construction : actionTerminals) {
			if (construction.getType() == ConstructionType.TOKEN) {
				if (token.getName().equals(construction.getName())) {
					return construction;
				}
			} else if (construction.getType() == ConstructionType.TEXT) {
				if (token.getText().equals(construction.getText())) {
					return construction;
				}
			}
		}
		return null;
	}

	@Override
	public final ParserAction getAction(int currentState,
			Construction construction) {
		ParserAction action = table.get(currentState).get(construction);
		if (action == null) {
			action = new ParserAction(ActionType.ERROR, -1);
		}
		return action;
	}

	@Override
	public final String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("=============\n");
		buffer.append("Parsing Table\n");
		buffer.append("=============\n");
		buffer.append("\n");
		buffer.append(toColumn("STATE: |"));
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
			if (construction.getType() == ConstructionType.TOKEN) {
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
		buffer.append(toColumn("-------|"));
		for (int i = 0; i < actionTerminals.size(); i++) {
			buffer.append(toColumn("--------"));
		}
		buffer.append(toColumn("-------|"));
		for (int i = 0; i < gotoNonTerminals.size(); i++) {
			buffer.append(toColumn("--------"));
		}
		buffer.append("\n");
		for (int state = 0; state < table.size(); state++) {
			buffer.append(toColumn(String.valueOf(state) + " |"));
			for (Construction construction : actionTerminals) {
				buffer.append(toColumn(getAction(state, construction)
						.toString()));
			}
			buffer.append(toColumn("|"));
			for (Construction construction : gotoNonTerminals) {
				buffer.append(toColumn(getAction(state, construction)
						.toString()));
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	private String toColumn(String s) {
		StringBuffer result = new StringBuffer();
		while (result.length() + s.length() < 8) {
			result.append(' ');
		}
		result.append(s);
		return result.toString();
	}
}
