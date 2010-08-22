package com.puresol.uhura.parser.lr;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishConstruction;
import com.puresol.uhura.grammar.production.ProductionConstruction;
import com.puresol.uhura.grammar.production.TextConstruction;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.parsetable.ActionType;
import com.puresol.uhura.parser.parsetable.ParserAction;
import com.puresol.uhura.parser.parsetable.ParserTable;
import com.puresol.uhura.parser.parsetable.StateTransitionGraph;

public class SLR1ParserTableTest extends TestCase {

	@Test
	public void testLRPamphletGrammar() {
		try {
			System.out.println("=====================");
			System.out.println("LR Pampghlet Grammar:");
			System.out.println("=====================");
			Grammar grammar = TestGrammars.getTestGrammarFromLR1Pamphlet();
			System.out.println("Productions:");
			grammar.printProductions();
			ParserTable table = new SLR1ParserTable(grammar);
			System.out.println(table.toString());

			// expected actions in table...
			ParserAction r1 = new ParserAction(ActionType.REDUCE, 1);
			ParserAction r2 = new ParserAction(ActionType.REDUCE, 2);
			ParserAction r3 = new ParserAction(ActionType.REDUCE, 3);
			ParserAction r4 = new ParserAction(ActionType.REDUCE, 4);
			ParserAction r5 = new ParserAction(ActionType.REDUCE, 5);
			ParserAction s2 = new ParserAction(ActionType.SHIFT, 2);
			ParserAction s3 = new ParserAction(ActionType.SHIFT, 3);
			ParserAction s5 = new ParserAction(ActionType.SHIFT, 5);
			ParserAction s6 = new ParserAction(ActionType.SHIFT, 6);
			ParserAction s8 = new ParserAction(ActionType.SHIFT, 8);
			ParserAction s9 = new ParserAction(ActionType.SHIFT, 9);
			ParserAction g1 = new ParserAction(ActionType.GOTO, 1);
			ParserAction g4 = new ParserAction(ActionType.GOTO, 4);
			ParserAction g7 = new ParserAction(ActionType.GOTO, 7);
			ParserAction accept = new ParserAction(ActionType.ACCEPT, -1);
			ParserAction error = new ParserAction(ActionType.ERROR, -1);
			Construction a = new TokenConstruction("a");
			Construction b = new TokenConstruction("b");
			Construction c = new TokenConstruction("c");
			Construction pS = new ProductionConstruction("S");
			Construction pA = new ProductionConstruction("A");
			Construction finish = FinishConstruction.getInstance();

			// checks...
			assertEquals(s3, table.getAction(0, b));
			assertEquals(error, table.getAction(0, a));
			assertEquals(error, table.getAction(0, c));
			assertEquals(error, table.getAction(0, finish));
			assertEquals(g1, table.getAction(0, pS));
			assertEquals(error, table.getAction(0, pA));

			assertEquals(s2, table.getAction(1, b));
			assertEquals(error, table.getAction(1, a));
			assertEquals(error, table.getAction(1, c));
			assertEquals(accept, table.getAction(1, finish));
			assertEquals(error, table.getAction(1, pS));
			assertEquals(error, table.getAction(1, pA));

			assertEquals(r1, table.getAction(2, b));
			assertEquals(error, table.getAction(2, a));
			assertEquals(r1, table.getAction(2, c));
			assertEquals(r1, table.getAction(2, finish));
			assertEquals(error, table.getAction(2, pS));
			assertEquals(error, table.getAction(2, pA));

			assertEquals(error, table.getAction(3, b));
			assertEquals(s6, table.getAction(3, a));
			assertEquals(error, table.getAction(3, c));
			assertEquals(error, table.getAction(3, finish));
			assertEquals(error, table.getAction(3, pS));
			assertEquals(g4, table.getAction(3, pA));

			assertEquals(error, table.getAction(4, b));
			assertEquals(s5, table.getAction(4, a));
			assertEquals(error, table.getAction(4, c));
			assertEquals(error, table.getAction(4, finish));
			assertEquals(error, table.getAction(4, pS));
			assertEquals(error, table.getAction(4, pA));

			assertEquals(r2, table.getAction(5, b));
			assertEquals(error, table.getAction(5, a));
			assertEquals(r2, table.getAction(5, c));
			assertEquals(r2, table.getAction(5, finish));
			assertEquals(error, table.getAction(5, pS));
			assertEquals(error, table.getAction(5, pA));

			assertEquals(s3, table.getAction(6, b));
			assertEquals(r4, table.getAction(6, a));
			assertEquals(error, table.getAction(6, c));
			assertEquals(error, table.getAction(6, finish));
			assertEquals(g7, table.getAction(6, pS));
			assertEquals(error, table.getAction(6, pA));

			assertEquals(s9, table.getAction(7, b));
			assertEquals(error, table.getAction(7, a));
			assertEquals(s8, table.getAction(7, c));
			assertEquals(error, table.getAction(7, finish));
			assertEquals(error, table.getAction(7, pS));
			assertEquals(error, table.getAction(7, pA));

			assertEquals(error, table.getAction(8, b));
			assertEquals(r3, table.getAction(8, a));
			assertEquals(error, table.getAction(8, c));
			assertEquals(error, table.getAction(8, finish));
			assertEquals(error, table.getAction(8, pS));
			assertEquals(error, table.getAction(8, pA));

			assertEquals(r1, table.getAction(9, b));
			assertEquals(r5, table.getAction(9, a));
			assertEquals(r1, table.getAction(9, c));
			assertEquals(r1, table.getAction(9, finish));
			assertEquals(error, table.getAction(9, pS));
			assertEquals(error, table.getAction(9, pA));

		} catch (ParserException e) {
			fail("No Exception was expected!");
		}
	}

	@Test
	public void testDragonBookGrammar() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			System.out.println("====================");
			System.out.println("Dragon Book Grammar:");
			System.out.println("====================");
			Grammar grammar = TestGrammars.getTestGrammarFromDragonBook();
			System.out.println("Productions:");
			grammar.printProductions();
			ParserTable table = new SLR1ParserTable(grammar);
			System.out.println(table.toString());

			// expected actions in table...
			ParserAction r1 = new ParserAction(ActionType.REDUCE, 1);
			ParserAction r2 = new ParserAction(ActionType.REDUCE, 2);
			ParserAction r3 = new ParserAction(ActionType.REDUCE, 3);
			ParserAction r4 = new ParserAction(ActionType.REDUCE, 4);
			ParserAction r5 = new ParserAction(ActionType.REDUCE, 5);
			ParserAction r6 = new ParserAction(ActionType.REDUCE, 6);
			ParserAction s2 = new ParserAction(ActionType.SHIFT, 2);
			ParserAction s4 = new ParserAction(ActionType.SHIFT, 4);
			ParserAction s6 = new ParserAction(ActionType.SHIFT, 6);
			ParserAction s8 = new ParserAction(ActionType.SHIFT, 8);
			ParserAction s11 = new ParserAction(ActionType.SHIFT, 11);
			ParserAction g1 = new ParserAction(ActionType.GOTO, 1);
			ParserAction g3 = new ParserAction(ActionType.GOTO, 3);
			ParserAction g5 = new ParserAction(ActionType.GOTO, 5);
			ParserAction g7 = new ParserAction(ActionType.GOTO, 7);
			ParserAction g9 = new ParserAction(ActionType.GOTO, 9);
			ParserAction g10 = new ParserAction(ActionType.GOTO, 10);
			ParserAction accept = new ParserAction(ActionType.ACCEPT, -1);
			ParserAction error = new ParserAction(ActionType.ERROR, -1);
			Construction lParen = new TextConstruction("(");
			Construction id = new TokenConstruction("id");
			Construction plus = new TextConstruction("+");
			Construction star = new TextConstruction("*");
			Construction rParen = new TextConstruction(")");
			Construction finish = FinishConstruction.getInstance();
			Construction pE = new ProductionConstruction("E");
			Construction pT = new ProductionConstruction("T");
			Construction pF = new ProductionConstruction("F");

			// checks...
			assertEquals(s6, table.getAction(0, lParen));
			assertEquals(s11, table.getAction(0, id));
			assertEquals(error, table.getAction(0, plus));
			assertEquals(error, table.getAction(0, star));
			assertEquals(error, table.getAction(0, rParen));
			assertEquals(error, table.getAction(0, finish));
			assertEquals(g1, table.getAction(0, pE));
			assertEquals(g9, table.getAction(0, pT));
			assertEquals(g10, table.getAction(0, pF));

			assertEquals(error, table.getAction(1, lParen));
			assertEquals(error, table.getAction(1, id));
			assertEquals(s2, table.getAction(1, plus));
			assertEquals(error, table.getAction(1, star));
			assertEquals(error, table.getAction(1, rParen));
			assertEquals(accept, table.getAction(1, finish));
			assertEquals(error, table.getAction(1, pE));
			assertEquals(error, table.getAction(1, pT));
			assertEquals(error, table.getAction(1, pF));

			assertEquals(s6, table.getAction(2, lParen));
			assertEquals(s11, table.getAction(2, id));
			assertEquals(error, table.getAction(2, plus));
			assertEquals(error, table.getAction(2, star));
			assertEquals(error, table.getAction(2, rParen));
			assertEquals(error, table.getAction(2, finish));
			assertEquals(error, table.getAction(2, pE));
			assertEquals(g3, table.getAction(2, pT));
			assertEquals(g10, table.getAction(2, pF));

			assertEquals(error, table.getAction(3, lParen));
			assertEquals(error, table.getAction(3, id));
			assertEquals(r1, table.getAction(3, plus));
			assertEquals(s4, table.getAction(3, star));
			assertEquals(r1, table.getAction(3, rParen));
			assertEquals(r1, table.getAction(3, finish));
			assertEquals(error, table.getAction(3, pE));
			assertEquals(error, table.getAction(3, pT));
			assertEquals(error, table.getAction(3, pF));

			assertEquals(s6, table.getAction(4, lParen));
			assertEquals(s11, table.getAction(4, id));
			assertEquals(error, table.getAction(4, plus));
			assertEquals(error, table.getAction(4, star));
			assertEquals(error, table.getAction(4, rParen));
			assertEquals(error, table.getAction(4, finish));
			assertEquals(error, table.getAction(4, pE));
			assertEquals(error, table.getAction(4, pT));
			assertEquals(g5, table.getAction(4, pF));

			assertEquals(error, table.getAction(5, lParen));
			assertEquals(error, table.getAction(5, id));
			assertEquals(error, table.getAction(5, plus));
			assertEquals(r3, table.getAction(5, star));
			assertEquals(error, table.getAction(5, rParen));
			assertEquals(r3, table.getAction(5, finish));
			assertEquals(error, table.getAction(5, pE));
			assertEquals(error, table.getAction(5, pT));
			assertEquals(error, table.getAction(5, pF));

			assertEquals(s6, table.getAction(6, lParen));
			assertEquals(s11, table.getAction(6, id));
			assertEquals(error, table.getAction(6, plus));
			assertEquals(error, table.getAction(6, star));
			assertEquals(error, table.getAction(6, rParen));
			assertEquals(error, table.getAction(6, finish));
			assertEquals(g7, table.getAction(6, pE));
			assertEquals(g9, table.getAction(6, pT));
			assertEquals(g10, table.getAction(6, pF));

			assertEquals(error, table.getAction(7, lParen));
			assertEquals(error, table.getAction(7, id));
			assertEquals(s2, table.getAction(7, plus));
			assertEquals(error, table.getAction(7, star));
			assertEquals(s8, table.getAction(7, rParen));
			assertEquals(error, table.getAction(7, finish));
			assertEquals(error, table.getAction(7, pE));
			assertEquals(error, table.getAction(7, pT));
			assertEquals(error, table.getAction(7, pF));

			assertEquals(error, table.getAction(8, lParen));
			assertEquals(error, table.getAction(8, id));
			assertEquals(error, table.getAction(8, plus));
			assertEquals(error, table.getAction(8, star));
			assertEquals(error, table.getAction(8, rParen));
			assertEquals(r5, table.getAction(8, finish));
			assertEquals(error, table.getAction(8, pE));
			assertEquals(error, table.getAction(8, pT));
			assertEquals(error, table.getAction(8, pF));

			assertEquals(error, table.getAction(9, lParen));
			assertEquals(error, table.getAction(9, id));
			assertEquals(r2, table.getAction(9, plus));
			assertEquals(s4, table.getAction(9, star));
			assertEquals(r2, table.getAction(9, rParen));
			assertEquals(r2, table.getAction(9, finish));
			assertEquals(error, table.getAction(9, pE));
			assertEquals(error, table.getAction(9, pT));
			assertEquals(error, table.getAction(9, pF));

			assertEquals(error, table.getAction(10, lParen));
			assertEquals(error, table.getAction(10, id));
			assertEquals(error, table.getAction(10, plus));
			assertEquals(r4, table.getAction(10, star));
			assertEquals(error, table.getAction(10, rParen));
			assertEquals(r4, table.getAction(10, finish));
			assertEquals(error, table.getAction(10, pE));
			assertEquals(error, table.getAction(10, pT));
			assertEquals(error, table.getAction(10, pF));

			assertEquals(error, table.getAction(11, lParen));
			assertEquals(error, table.getAction(11, id));
			assertEquals(error, table.getAction(11, plus));
			assertEquals(error, table.getAction(11, star));
			assertEquals(error, table.getAction(11, rParen));
			assertEquals(r6, table.getAction(11, finish));
			assertEquals(error, table.getAction(11, pE));
			assertEquals(error, table.getAction(11, pT));
			assertEquals(error, table.getAction(11, pF));
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
