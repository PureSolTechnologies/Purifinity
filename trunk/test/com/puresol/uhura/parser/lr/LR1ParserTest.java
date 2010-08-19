package com.puresol.uhura.parser.lr;

import java.util.Properties;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TokenConstruction;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.lr.LR0Parser;

public class LR1ParserTest extends TestCase {

	@Test
	public void test() {
		try {
			TokenStream tokens = new TokenStream();
			tokens.add(new Token("INTEGER", "1", Visibility.VISIBLE));
			tokens.add(new Token("PLUS", "+", Visibility.VISIBLE));
			tokens.add(new Token("INTEGER", "2", Visibility.VISIBLE));

			ProductionSet ruleSet = new ProductionSet();
			Production rule = new Production("EXPRESSION");
			rule.addElement(new TokenConstruction("INTEGER"));
			rule.addElement(new TokenConstruction("PLUS"));
			rule.addElement(new TokenConstruction("INTEGER"));
			ruleSet.addRule(rule);

			LR0Parser parser = new LR0Parser(new Properties());
			parser.setTokenStream(tokens);
			parser.setProductions(ruleSet);

			SyntaxTree tree = parser.call();

			assertNotNull(tree);
			assertEquals("EXPRESSION", tree.getName());
			assertEquals(3, tree.getChildren().size());
			assertEquals(0, tree.getChildren().get(0).getChildren().size());
			assertEquals("INTEGER", tree.getChildren().get(0).getName());
			assertEquals(0, tree.getChildren().get(1).getChildren().size());
			assertEquals("PLUS", tree.getChildren().get(1).getName());
			assertEquals(0, tree.getChildren().get(2).getChildren().size());
			assertEquals("INTEGER", tree.getChildren().get(2).getName());
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
