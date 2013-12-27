package com.puresoltechnologies.parsers.impl.parser.lr;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.grammar.TestGrammars;
import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.parser.functions.Closure1;
import com.puresoltechnologies.parsers.impl.parser.functions.First;
import com.puresoltechnologies.parsers.impl.parser.functions.Goto1;
import com.puresoltechnologies.parsers.impl.parser.lr.LR1ItemSetCollection;

public class LR1ItemSetCollectionTest {

    @Test
    public void testLRPamphletGrammar() {
	try {
	    System.out.println("====================");
	    System.out.println("LR Pamphlet Grammar:");
	    System.out.println("====================");
	    Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();

	    First first = new First(grammar);
	    Closure1 closure1 = new Closure1(grammar, first);
	    Goto1 goto1 = new Goto1(closure1);
	    LR1ItemSetCollection transitionGraph = new LR1ItemSetCollection(
		    grammar, closure1, goto1);
	    System.out.println(transitionGraph);
	} catch (GrammarException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }

    @Test
    public void testDragonBookGrammar() {
	try {
	    System.out.println("====================");
	    System.out.println("Dragon Book Grammar:");
	    System.out.println("====================");
	    Grammar grammar = TestGrammars.getLR1TestGrammarFromDragonBook();

	    First first = new First(grammar);
	    Closure1 closure1 = new Closure1(grammar, first);
	    Goto1 goto1 = new Goto1(closure1);
	    LR1ItemSetCollection transitionGraph = new LR1ItemSetCollection(
		    grammar, closure1, goto1);
	    System.out.println(transitionGraph);
	} catch (GrammarException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }

}