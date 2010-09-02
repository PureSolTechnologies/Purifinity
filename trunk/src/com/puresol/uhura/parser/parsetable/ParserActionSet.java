package com.puresol.uhura.parser.parsetable;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.puresol.uhura.grammar.GrammarException;

public class ParserActionSet {

	private static final Set<ParserAction> actions = new CopyOnWriteArraySet<ParserAction>();

	public void addAction(ParserAction action) {
		actions.add(action);
	}

	public ParserAction getAction() throws GrammarException {
		if (actions.size() == 0) {
			return new ParserAction(ActionType.ERROR, -1);
		}
		if (actions.size() == 1) {
			return actions.iterator().next();
		}
		ParserAction result = null;
		Iterator<ParserAction> iter = actions.iterator();
		while (iter.hasNext()) {
			ParserAction action = iter.next();
			if (action.isPreferred()) {
				if (action == null) {
					result = action;
				} else {
					throw new GrammarException("Ambiguous grammar!");
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Iterator<ParserAction> iter = actions.iterator();
		boolean first = false;
		while (iter.hasNext()) {
			ParserAction action = iter.next();
			if (first) {
				first = false;
			} else {
				buffer.append(",");
			}
			buffer.append(action.toString());
		}
		return buffer.toString();
	}

}
