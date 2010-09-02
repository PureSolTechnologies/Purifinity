package com.puresol.uhura.parser.parsetable;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.puresol.uhura.grammar.GrammarException;

public class ParserActionSet {

	public static ParserActionSet getErrorSet() {
		ParserActionSet set = new ParserActionSet();
		set.addAction(new ParserAction(ActionType.ERROR, -1));
		return set;
	}

	private final List<ParserAction> actions = new CopyOnWriteArrayList<ParserAction>();

	public void addAction(ParserAction action) {
		if (!actions.contains(action)) {
			actions.add(action);
		}
	}

	public ParserAction getAction() throws GrammarException {
		if (actions.size() == 0) {
			return new ParserAction(ActionType.ERROR, -1);
		}
		if (actions.size() == 1) {
			return actions.iterator().next();
		}
		throw new GrammarException("Can not choose for one of '" + toString()
				+ "'!");
	}

	public ParserAction getAction(int id) {
		return actions.get(id);
	}

	public int getActionNumber() {
		return actions.size();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Iterator<ParserAction> iter = actions.iterator();
		boolean first = true;
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
