package com.puresol.uhura.parser.parsetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.puresol.uhura.grammar.GrammarException;

public class ParserActionSet implements Serializable {

	private static final long serialVersionUID = -724846770893802355L;

	public static ParserActionSet getErrorSet() {
		ParserActionSet set = new ParserActionSet();
		set.addAction(new ParserAction(ActionType.ERROR, -1));
		return set;
	}

	private final List<ParserAction> actions = new ArrayList<ParserAction>();

	public ParserActionSet() {
		actions.add(new ParserAction(ActionType.ERROR, -1));
	}

	public void addAction(ParserAction action) {
		if (action.getAction() == ActionType.ERROR) {
			return;
		}
		if (actions.get(0).getAction() == ActionType.ERROR) {
			actions.clear();
		}
		if (!actions.contains(action)) {
			actions.add(action);
		}
	}

	public void addActions(ParserActionSet actionSet) {
		for (int id = 0; id < actionSet.getActionNumber(); id++) {
			addAction(actionSet.getAction(id));
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
