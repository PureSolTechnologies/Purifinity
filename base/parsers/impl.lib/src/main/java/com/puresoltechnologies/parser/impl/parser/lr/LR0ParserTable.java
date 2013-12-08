package com.puresoltechnologies.parser.impl.parser.lr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.puresoltechnologies.commons.utils.FileUtilities;
import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.parser.impl.grammar.GrammarException;
import com.puresoltechnologies.parser.impl.grammar.production.Construction;
import com.puresoltechnologies.parser.impl.grammar.production.FinishTerminal;
import com.puresoltechnologies.parser.impl.grammar.production.NonTerminal;
import com.puresoltechnologies.parser.impl.grammar.production.Terminal;
import com.puresoltechnologies.parser.impl.parser.functions.Closure0;
import com.puresoltechnologies.parser.impl.parser.functions.Goto0;
import com.puresoltechnologies.parser.impl.parser.items.LR0Item;
import com.puresoltechnologies.parser.impl.parser.items.LR0ItemSet;
import com.puresoltechnologies.parser.impl.parser.parsetable.AbstractParserTable;
import com.puresoltechnologies.parser.impl.parser.parsetable.ActionType;
import com.puresoltechnologies.parser.impl.parser.parsetable.ParserAction;
import com.puresoltechnologies.parser.impl.parser.parsetable.ParserActionSet;

public class LR0ParserTable extends AbstractParserTable {

	private static final long serialVersionUID = 4063891308079169101L;

	public LR0ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	@Override
	protected void calculate() throws GrammarException {
		Closure0 closure0 = new Closure0(getGrammar());
		Goto0 goto0 = new Goto0(closure0);
		LR0ItemSetCollection transitionGraph = new LR0ItemSetCollection(
				getGrammar(), closure0, goto0);
		LR0StateTransitions stateTransitions = new LR0StateTransitions(
				transitionGraph, goto0);
		addShiftAndGotos(transitionGraph, stateTransitions);
		addReduceAndAccept(transitionGraph);
	}

	private void addShiftAndGotos(LR0ItemSetCollection itemSetCollection,
			LR0StateTransitions stateTransitions) throws GrammarException {
		for (int stateId = 0; stateId < itemSetCollection.getStateNumber(); stateId++) {
			Map<Construction, Integer> transitions = stateTransitions
					.getTransitions(stateId);
			for (Construction construction : transitions.keySet()) {
				if (construction.isTerminal()) {
					addAction(stateId, construction, new ParserAction(
							ActionType.SHIFT, transitions.get(construction)));
					if (!getActionTerminals().contains(construction)) {
						addActionTerminal((Terminal) construction);
					}
				} else {
					addAction(stateId, construction, new ParserAction(
							ActionType.GOTO, transitions.get(construction)));
					if (!getGotoNonTerminals().contains(construction)) {
						addGotoNonTerminal((NonTerminal) construction);
					}
				}
			}
		}
	}

	private void addReduceAndAccept(LR0ItemSetCollection transitionGraph)
			throws GrammarException {
		Grammar grammar = getGrammar();
		for (int stateId = 0; stateId < transitionGraph.getStateNumber(); stateId++) {
			LR0ItemSet itemSet = transitionGraph.getItemSet(stateId);
			for (LR0Item item : itemSet.getAllItems()) {
				if (item.getNext() != null) {
					continue;
				}
				if (item.getProduction()
						.equals(grammar.getProductions().get(0))) {
					addActionTerminal(FinishTerminal.getInstance());
					addAction(stateId, FinishTerminal.getInstance(),
							new ParserAction(ActionType.ACCEPT, -1));
				} else {
					for (Construction construction : getActionTerminals()) {
						addAction(stateId, construction, new ParserAction(
								ActionType.REDUCE, grammar.getProductions()
										.getId(item.getProduction())));
					}
				}
			}
		}
	}

	@Override
	public void generateInspectionInformation(File directory)
			throws IOException, GrammarException {
		directory = new File(directory, getGrammar().getName());
		if (!directory.exists()) {
			if (!directory.mkdirs()) {
				throw new IOException("Could not create ouput directory!");
			}
		}
		Closure0 closure0 = new Closure0(getGrammar());
		Goto0 goto0 = new Goto0(closure0);
		LR0ItemSetCollection itemSetCollection = new LR0ItemSetCollection(
				getGrammar(), closure0, goto0);
		FileUtilities.writeFile(directory, new File("Grammar"), getGrammar()
				.toString());
		FileUtilities.writeFile(directory, new File("Closure0"),
				closure0.toString());
		FileUtilities.writeFile(directory, new File("Goto0"), goto0.toString());
		FileUtilities.writeFile(directory, new File("ItemSetCollection"),
				itemSetCollection.toString());
		writeTable(directory, itemSetCollection);
	}

	private void writeTable(File directory,
			LR0ItemSetCollection itemSetCollection) throws IOException,
			GrammarException {
		FileWriter writer = new FileWriter(new File(directory,
				"parser_actions.txt"));
		for (int state = 0; state < getStateCount(); state++) {
			writer.write("-----------------------------------------------------------------------------\n");
			writer.write("\n");
			writer.write("================\n");
			writer.write("State " + state + ":\n");
			writer.write("================\n");
			writer.write(itemSetCollection.getItemSet(state).toString());
			writer.write("\n");
			for (Terminal terminal : getActionTerminals()) {
				ParserActionSet actions = getActionSet(state, terminal);
				if ((actions.getActionNumber() == 1)
						&& (actions.getAction().getAction() == ActionType.ERROR)) {
					continue;
				}
				writer.write(terminal.toString());
				writer.write(":\n");
				if (actions.getActionNumber() > 1) {
					writer.write("\tCONFLICT");
					for (int i = 0; i < actions.getActionNumber(); i++) {
						writer.write("!");
					}
					writer.write("\n");
				}
				for (int i = 0; i < actions.getActionNumber(); i++) {
					ParserAction action = actions.getAction(i);
					writer.write("\t");
					writer.write(action.toString());
					if (action.getAction() == ActionType.REDUCE) {
						writer.write("\t");
						writer.write(getGrammar().getProduction(
								action.getParameter()).toString());
					}
					writer.write("\n");
				}
			}
		}
		writer.close();
	}

}
