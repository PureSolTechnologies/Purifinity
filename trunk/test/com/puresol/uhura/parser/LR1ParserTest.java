package com.puresol.uhura.parser;

import java.util.Properties;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.lexer.Visibility;
import com.puresol.uhura.parser.lr1.LR1Parser;

public class LR1ParserTest extends TestCase {

	@Test
	public void test() {
		try {
			TokenStream tokens = new TokenStream();
			tokens.add(new Token("INTEGER", "1", Visibility.VISIBLE));
			tokens.add(new Token("PLUS", "+", Visibility.VISIBLE));
			tokens.add(new Token("INTEGER", "2", Visibility.VISIBLE));

			ParserRuleSet ruleSet = new ParserRuleSet();
			ParserRule rule = new ParserRule("expression");
			rule.addElement(new ParserRuleElement("INTEGER",
					ParserRuleElementType.TOKEN));
			rule.addElement(new ParserRuleElement("PLUS",
					ParserRuleElementType.TOKEN));
			rule.addElement(new ParserRuleElement("INTEGER",
					ParserRuleElementType.TOKEN));
			ruleSet.addRule(rule);

			LR1Parser parser = new LR1Parser(new Properties());
			parser.setTokenStream(tokens);
			parser.setRules(ruleSet);

			SyntaxTree tree = parser.call();

			assertNotNull(tree);
			assertEquals("expression", tree.getName());
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
		}
	}

}
