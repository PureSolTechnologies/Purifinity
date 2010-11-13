package utils;

import java.io.File;
import java.io.FileWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.parser.functions.Closure1;
import com.puresol.uhura.parser.functions.First;
import com.puresol.uhura.parser.functions.Follow;
import com.puresol.uhura.parser.functions.Goto1;
import com.puresol.uhura.parser.lr.LR1ItemSetCollection;
import com.puresol.uhura.parser.lr.LR1ParserTable;
import com.puresol.uhura.parser.lr.LR1StateTransitions;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.ParserActionSet;

public class GrammarInspector {

	public static void main(String args[]) {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);

			Grammar grammar = JavaGrammar.getInstance().getGrammar();
			FileWriter writer = new FileWriter(new File(
					"grammar_inspection/grammar.txt"));
			writer.write(grammar.toString());
			writer.close();

			First first = new First(grammar);
			writer = new FileWriter(new File("grammar_inspection/first.txt"));
			writer.write(first.toString());
			writer.close();

			Follow follow = new Follow(grammar, first);
			writer = new FileWriter(new File("grammar_inspection/follow.txt"));
			writer.write(follow.toString());
			writer.close();

			Closure1 closure1 = new Closure1(grammar, first);
			Goto1 goto1 = new Goto1(closure1);
			LR1ItemSetCollection lr1ItemSetCollection = new LR1ItemSetCollection(
					grammar, closure1, goto1);
			LR1StateTransitions lr1Transitions = new LR1StateTransitions(
					lr1ItemSetCollection, goto1);

			writer = new FileWriter(new File("grammar_inspection/states.txt"));
			writer.write(lr1ItemSetCollection.toString());
			writer.close();

			writer = new FileWriter(new File(
					"grammar_inspection/transitions.txt"));
			for (int state = 0; state < lr1ItemSetCollection.getStateNumber(); state++) {
				writer.write("-----------------------------------------------------------------------------\n");
				writer.write("\n");
				writer.write("================\n");
				writer.write("State " + state + ":\n");
				writer.write("================\n");
				writer.write(lr1ItemSetCollection.getItemSet(state).toString());
				writer.write("\n");
				for (Construction construction : lr1Transitions.getTransitions(
						state).keySet()) {
					int set = lr1Transitions.getTransition(state, construction);
					writer.write(construction.toShortString() + " -> " + set);
					writer.write("\n");
				}
				writer.write("\n");
				writer.write("-----------------------------------------------------------------------------\n");
			}
			writer.close();

			LR1ParserTable parserTable = new LR1ParserTable(grammar);
			writer = new FileWriter(new File(
					"grammar_inspection/parser_actions.txt"));
			for (int state = 0; state < lr1ItemSetCollection.getStateNumber(); state++) {
				writer.write("-----------------------------------------------------------------------------\n");
				writer.write("\n");
				writer.write("================\n");
				writer.write("State " + state + ":\n");
				writer.write("================\n");
				writer.write(lr1ItemSetCollection.getItemSet(state).toString());
				writer.write("\n");
				for (Terminal terminal : parserTable.getActionTerminals()) {
					ParserActionSet actions = parserTable.getActionSet(state,
							terminal);
					if ((actions.getActionNumber() == 1)
							&& (actions.getAction().getAction() == ActionType.ERROR)) {
						continue;
					}
					writer.write(terminal.toString());
					writer.write(":\n");
					if (actions.getActionNumber() > 1) {
						writer.write("\tCONFLICT!!!\n");
					}
					for (int i = 0; i < actions.getActionNumber(); i++) {
						ParserAction action = actions.getAction(i);
						writer.write("\t");
						writer.write(action.toString());
						writer.write("\n");
					}
				}
			}
			writer.close();

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
