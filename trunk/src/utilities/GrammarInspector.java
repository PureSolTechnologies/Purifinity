package utilities;

import java.io.File;
import java.io.FileWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.Closure1;
import com.puresol.uhura.parser.functions.First;
import com.puresol.uhura.parser.functions.Follow;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.uhura.parser.lr.LALR1ItemSetCollection;
import com.puresol.uhura.parser.lr.LR0ItemSetCollection;
import com.puresol.uhura.parser.lr.LR0StateTransitions;

public class GrammarInspector {

	public static void main(String args[]) {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);

			Grammar grammar = FortranGrammar.getInstance().getGrammar();
			grammar.createWithNewStartProduction("signed-int-literal-constant");
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

			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			LR0ItemSetCollection lr0ItemSetCollection = new LR0ItemSetCollection(
					grammar, closure0, goto0);
			LR0StateTransitions lr0Transitions = new LR0StateTransitions(
					lr0ItemSetCollection, goto0);
			Closure1 closure1 = new Closure1(grammar, first);
			LALR1ItemSetCollection itemSetCollection = new LALR1ItemSetCollection(
					grammar, lr0ItemSetCollection, lr0Transitions, closure1);
			// LALR1ParserTable table = new LALR1ParserTable(grammar);

			writer = new FileWriter(new File("grammar_inspection/states.txt"));
			writer.write(itemSetCollection.toString());
			writer.close();

			writer = new FileWriter(new File(
					"grammar_inspection/parser_actions.txt"));
			for (int state = 0; state < itemSetCollection.getStateNumber(); state++) {
				writer.write("-----------------------------------------------------------------------------\n");
				writer.write("\n");
				writer.write("================\n");
				writer.write("State " + state + ":\n");
				writer.write("================\n");
				writer.write(itemSetCollection.getItemSet(state).toString());
				writer.write("\n");
				for (Construction construction : lr0Transitions.getTransitions(
						state).keySet()) {
					int set = lr0Transitions.getTransition(state, construction);
					writer.write(construction.toShortString() + " -> " + set);
					writer.write("\n");
				}
				writer.write("\n");
				writer.write("-----------------------------------------------------------------------------\n");
			}
			writer.close();

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
