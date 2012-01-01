package com.puresol.uhura.parser.functions;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Iterator;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.Construction;
import com.puresol.uhura.grammar.production.FinishTerminal;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.PersistenceTester;

public class FollowTest {

	@Test
	public void testPersistence() {
		Grammar grammar = TestGrammars.getLR1TestGrammarFromDragonBook();
		First first = new First(grammar);
		Follow follow = new Follow(grammar, first);
		File file = new File("test", FileUtilities.classToRelativePackagePath(
				Follow.class).toString()
				+ ".persist");
		PersistenceTester.test(follow, file);
	}

	@Test
	public void testLRPamphletGrammar() {
		System.out.println("=====================");
		System.out.println("LR Pampghlet Grammar:");
		System.out.println("=====================");
		Grammar grammar = TestGrammars.getGrammarFromLRkPamphlet();
		System.out.println("Productions:");
		System.out.println(grammar.toProductionsString());

		Follow follow = new Follow(grammar, new First(grammar));
		System.out.println("Follow:");
		System.out.println(follow.toString());

		Construction productionZ = new NonTerminal("Z");
		Construction productionS = new NonTerminal("S");
		Construction productionA = new NonTerminal("A");
		Construction finish = FinishTerminal.getInstance();
		Construction a = new Terminal("a", null);
		Construction b = new Terminal("b", null);
		Construction c = new Terminal("c", null);

		assertEquals(1, follow.get(productionZ).size());
		Iterator<Terminal> iterator = follow.get(productionZ).iterator();
		assertEquals(finish, iterator.next());

		assertEquals(1, follow.get(productionA).size());
		iterator = follow.get(productionA).iterator();
		assertEquals(a, iterator.next());

		assertEquals(3, follow.get(productionS).size());
		iterator = follow.get(productionS).iterator();
		assertEquals(finish, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
	}

	@Test
	public void testDragonBookGrammar() {
		System.out.println("====================");
		System.out.println("Dragon Book Grammar:");
		System.out.println("====================");
		Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
		System.out.println("Productions:");
		System.out.println(grammar.toProductionsString());

		Follow follow = new Follow(grammar, new First(grammar));
		System.out.println("Follow:");
		System.out.println(follow.toString());

		Construction productionZ = new NonTerminal("Z");
		Construction productionE = new NonTerminal("E");
		Construction productionT = new NonTerminal("T");
		Construction productionF = new NonTerminal("F");
		Construction finish = FinishTerminal.getInstance();
		Construction plus = new Terminal("PLUS", "+");
		Construction star = new Terminal("STAR", "*");
		Construction rParen = new Terminal("RPAREN", ")");

		assertEquals(3, follow.get(productionE).size());
		Iterator<Terminal> iterator = follow.get(productionE).iterator();
		assertEquals(finish, iterator.next());
		assertEquals(plus, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(4, follow.get(productionT).size());
		iterator = follow.get(productionT).iterator();
		assertEquals(finish, iterator.next());
		assertEquals(plus, iterator.next());
		assertEquals(star, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(4, follow.get(productionF).size());
		iterator = follow.get(productionF).iterator();
		assertEquals(finish, iterator.next());
		assertEquals(plus, iterator.next());
		assertEquals(star, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(1, follow.get(productionZ).size());
		iterator = follow.get(productionZ).iterator();
		assertEquals(finish, iterator.next());

	}

	@Test
	public void testLLDragonBookGrammar() {
		System.out.println("=======================");
		System.out.println("Dragon Book LL Grammar:");
		System.out.println("=======================");
		Grammar grammar = TestGrammars.getLLGrammarFromDragonBook();
		System.out.println("Productions:");
		System.out.println(grammar.toProductionsString());

		Follow follow = new Follow(grammar, new First(grammar));
		System.out.println("Follow:");
		System.out.println(follow.toString());

		Construction productionE = new NonTerminal("E");
		Construction productionES = new NonTerminal("E'");
		Construction productionT = new NonTerminal("T");
		Construction productionTS = new NonTerminal("T'");
		Construction productionF = new NonTerminal("F");
		Construction finish = FinishTerminal.getInstance();
		Construction plus = new Terminal("PLUS", "+");
		Construction star = new Terminal("STAR", "*");
		Construction rParen = new Terminal("RPAREN", ")");

		assertEquals(2, follow.get(productionE).size());
		Iterator<Terminal> iterator = follow.get(productionE).iterator();
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(2, follow.get(productionES).size());
		iterator = follow.get(productionES).iterator();
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(3, follow.get(productionT).size());
		iterator = follow.get(productionT).iterator();
		assertEquals(plus, iterator.next());
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(3, follow.get(productionTS).size());
		iterator = follow.get(productionTS).iterator();
		assertEquals(plus, iterator.next());
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());

		assertEquals(4, follow.get(productionF).size());
		iterator = follow.get(productionF).iterator();
		assertEquals(star, iterator.next());
		assertEquals(plus, iterator.next());
		assertEquals(finish, iterator.next());
		assertEquals(rParen, iterator.next());
	}
}
