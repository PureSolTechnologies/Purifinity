package com.puresol.uhura.parser;

import java.util.Properties;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.TokenProductionElement;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.lr1.LR1Parser;

public class LR1ParserTest extends TestCase {

	@Test
	public void test() {
		try {
			TokenStream tokens = new TokenStream();
			tokens.add(new Token(1, "1", Visibility.VISIBLE));
			tokens.add(new Token(2, "+", Visibility.VISIBLE));
			tokens.add(new Token(1, "2", Visibility.VISIBLE));

			ProductionSet ruleSet = new ProductionSet();
			Production rule = new Production(3);
			rule.addElement(new TokenProductionElement(1));
			rule.addElement(new TokenProductionElement(2));
			rule.addElement(new TokenProductionElement(1));
			ruleSet.addRule(rule);

			LR1Parser parser = new LR1Parser(new Properties());
			parser.setTokenStream(tokens);
			parser.setProductions(ruleSet);

			SyntaxTree tree = parser.call();

			assertNotNull(tree);
			assertEquals(3, tree.getTypeId());
			assertEquals(3, tree.getChildren().size());
			assertEquals(0, tree.getChildren().get(0).getChildren().size());
			assertEquals(1, tree.getChildren().get(0).getTypeId());
			assertEquals(0, tree.getChildren().get(1).getChildren().size());
			assertEquals(2, tree.getChildren().get(1).getTypeId());
			assertEquals(0, tree.getChildren().get(2).getChildren().size());
			assertEquals(1, tree.getChildren().get(2).getTypeId());
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
