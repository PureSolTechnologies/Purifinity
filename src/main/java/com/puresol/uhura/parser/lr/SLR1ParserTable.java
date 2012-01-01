package com.puresol.uhura.parser.lr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.First;
import com.puresol.uhura.parser.functions.Follow;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.uhura.parser.items.LR0Item;
import com.puresol.uhura.parser.items.LR0ItemSet;
import com.puresol.uhura.parser.parsetable.AbstractParserTable;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.ParserActionSet;
import com.puresol.utils.FileUtilities;

public class SLR1ParserTable extends AbstractParserTable {

	private static final long serialVersionUID = 519035758380605579L;

	private static final Logger logger = Logger
			.getLogger(SLR1ParserTable.class);

	public SLR1ParserTable(Grammar grammar) throws GrammarException {
		super(grammar);
	}

	protected void calculate() throws GrammarException {
		logger.debug("Calculate item set collection...");
		First first = new First(getGrammar());
		Follow follow = new Follow(getGrammar(), first);
		Closure0 closure0 = new Closure0(getGrammar());
		Goto0 goto0 = new Goto0(closure0);
		LR0ItemSetCollection itemSetCollection = new LR0ItemSetCollection(
				getGrammar(), closure0, goto0);
		logger.debug("Create parser table...");
		for (int state = 0; state < itemSetCollection.getStateNumber(); state++) {
			if (logger.isTraceEnabled()) {
				logger.trace("state: " + state + "/"
						+ itemSetCollection.getStateNumber());
			}
			LR0ItemSet lr0ItemSet = itemSetCollection.getItemSet(state);
			for (LR0Item lr0Item : lr0ItemSet.getAllItems()) {
				if (lr0Item.hasNext()) {
					Construction next = lr0Item.getNext();
					LR0ItemSet targetSet = goto0.calc(lr0ItemSet, next);
					int targetState = itemSetCollection.getStateId(targetSet);
					if (next.isTerminal()) {
						addAction(state, next, new ParserAction(
								ActionType.SHIFT, targetState));
						addActionTerminal((Terminal) next);
					} else {
						addAction(state, next, new ParserAction(
								ActionType.GOTO, targetState));
						addGotoNonTerminal((NonTerminal) next);
					}
				} else if (lr0Item.getProduction().equals(
						getGrammar().getProductions().get(0))) {
					// has not next and is not start production
					addAction(state, FinishTerminal.getInstance(),
							new ParserAction(ActionType.ACCEPT, -1));
					addActionTerminal(FinishTerminal.getInstance());
				} else { // has not next and is not start production
					for (Construction lookahead : follow.get(new NonTerminal(
							lr0Item.getProduction().getName()))) {
						addAction(state, lookahead, new ParserAction(
								ActionType.REDUCE, lr0Item.getProduction()
										.getId()));
					}
				}
			}
		}
		logger.debug("done.");
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
		First first = new First(getGrammar());
		Follow follow = new Follow(getGrammar(), first);
		Closure0 closure0 = new Closure0(getGrammar());
		Goto0 goto0 = new Goto0(closure0);
		LR0ItemSetCollection itemSetCollection = new LR0ItemSetCollection(
				getGrammar(), closure0, goto0);
		FileUtilities.writeFile(directory, new File("Grammar"), getGrammar()
				.toString());
		FileUtilities.writeFile(directory, new File("First"), first.toString());
		FileUtilities.writeFile(directory, new File("Follow"),
				follow.toString());
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
