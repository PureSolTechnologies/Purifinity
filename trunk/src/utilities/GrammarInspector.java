package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.trees.TreePrinter;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.parser.lr.LR1ParserTable;
import com.puresol.uhura.parser.parsetable.First;
import com.puresol.uhura.parser.parsetable.Follow;
import com.puresol.uhura.parser.parsetable.LALR1StateTransitionGraph;
import com.puresol.uhura.parser.parsetable.LR1StateTransitionGraph;
import com.puresol.uhura.parser.parsetable.ParserActionSet;

public class GrammarInspector {

	public static void main(String args[]) {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			new LALR1StateTransitionGraph(FortranGrammar.getInstance()
					.getGrammar());
			// Grammar grammar = FortranGrammar.getInstance().getGrammar();
			// // grammar =
			// grammar.createWithNewStartProduction("execution-part");
			// FileWriter writer = new FileWriter(new File(
			// "grammar_inspection/grammar.txt"));
			// writer.write(grammar.toString());
			// writer.close();
			//
			// First first = new First(grammar);
			// writer = new FileWriter(new
			// File("grammar_inspection/first.txt"));
			// writer.write(first.toString());
			// writer.close();
			//
			// Follow follow = new Follow(grammar);
			// writer = new FileWriter(new
			// File("grammar_inspection/follow.txt"));
			// writer.write(follow.toString());
			// writer.close();
			//
			// LR1ParserTable parserTable = new LR1ParserTable(grammar);
			// LR1StateTransitionGraph transitions = parserTable
			// .getTransitionGraph();
			// writer = new FileWriter(new
			// File("grammar_inspection/states.txt"));
			// writer.write(transitions.toString());
			// writer.close();
			//
			// writer = new FileWriter(new File(
			// "grammar_inspection/parser_actions.txt"));
			// for (int state = 0; state < transitions.getStateNumber();
			// state++) {
			// writer.write("-----------------------------------------------------------------------------\n");
			// writer.write("\n");
			// writer.write("================\n");
			// writer.write("State " + state + ":\n");
			// writer.write("================\n");
			// writer.write(transitions.getItemSet(state).toString());
			// writer.write("\n");
			// for (Construction construction : parserTable
			// .getPossibleActions(state).keySet()) {
			// ParserActionSet set = parserTable.getActionSet(state,
			// construction);
			// writer.write(construction.toShortString() + " -> "
			// + set.toString());
			// writer.write("\n");
			// if (set.getActionNumber() > 1) {
			// writer.write("\tCONFLICT!\n");
			// }
			// }
			// writer.write("\n");
			// writer.write("-----------------------------------------------------------------------------\n");
			// }
			// writer.close();
			//
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
