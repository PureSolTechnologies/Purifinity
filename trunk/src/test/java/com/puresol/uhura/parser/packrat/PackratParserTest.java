package com.puresol.uhura.parser.packrat;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.production.Terminal;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.parser.ParserTree;

public class PackratParserTest {

	private static Grammar iiiGrammar;

	@BeforeClass
	public static void setup() throws Throwable {
		Properties options = new Properties();
		TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
		tokenDefinitions.addDefinition(new TokenDefinition("i", "i"));
		ProductionSet productions = new ProductionSet();
		Production start = new Production("_START_");
		start.addConstruction(new NonTerminal("I"));
		productions.add(start);

		Production i1 = new Production("I");
		i1.addConstruction(new NonTerminal("I"));
		i1.addConstruction(new Terminal("i", "i"));
		productions.add(i1);

		Production i2 = new Production("I");
		i2.addConstruction(new Terminal("i", "i"));
		productions.add(i2);

		iiiGrammar = new Grammar(options, tokenDefinitions, productions);
	}

	// @Test
	// public void testInstance() {
	// assertNotNull(new PackratParser(
	// TestGrammars.getLLGrammarFromDragonBook()));
	// }
	//
	// @Test
	// public void testPatternAndMatcherForCertainBehavior() {
	// Pattern pattern = Pattern.compile("^\t");
	// Matcher matcher = pattern.matcher(" \t ".substring(1));
	// assertTrue(matcher.find());
	// }

	// @Test
	// public void testProcessWhitespaces() throws Throwable {
	//
	// Grammar grammar = TestGrammars.getLLGrammarFromDragonBook();
	// TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
	// tokenDefinitions.addDefinition(new TokenDefinition("Space", " ",
	// Visibility.IGNORED));
	// tokenDefinitions.addDefinition(new TokenDefinition("Tab", "\\t",
	// Visibility.IGNORED));
	// PackratParser parser = new PackratParser(grammar);
	//
	// IntrospectionUtilities.setField(parser, "text", " \t ");
	//
	// ParserTree parserTree = new ParserTree("");
	// IntrospectionUtilities.invokeMethod(parser, "processIgnoredTokens",
	// parserTree);
	// assertNotNull(parserTree);
	//
	// assertEquals(3, parserTree.getChildren().size());
	// Token token0 = parserTree.getChildren().get(0).getToken();
	// Token token1 = parserTree.getChildren().get(1).getToken();
	// Token token2 = parserTree.getChildren().get(2).getToken();
	//
	// assertEquals("Space", token0.getName());
	// assertEquals("Tab", token1.getName());
	// assertEquals("Space", token2.getName());
	//
	// assertEquals(0, token0.getMetaData().getId());
	// assertEquals(1, token1.getMetaData().getId());
	// assertEquals(2, token2.getMetaData().getId());
	//
	// assertEquals(0, token0.getMetaData().getPos());
	// assertEquals(1, token1.getMetaData().getPos());
	// assertEquals(2, token2.getMetaData().getPos());
	//
	// assertEquals(1, token0.getMetaData().getLine());
	// assertEquals(1, token1.getMetaData().getLine());
	// assertEquals(1, token2.getMetaData().getLine());
	// }

	// @Test
	// public void testSampleParse() throws Throwable {
	// Grammar grammar = TestGrammars.getLLGrammarFromDragonBook();
	// assertNotNull(grammar);
	// Production production = new Production("_START_");
	// production.addConstruction(new NonTerminal("E"));
	// grammar.getProductions().add(production);
	// PackratParser parser = new PackratParser(grammar);
	// ParserTree parseTree = parser.parse("(1+2)*3+4*5", "TEST");
	// assertNotNull(parseTree);
	// // TreePrinter printer = new TreePrinter(System.out);
	// // printer.println(parseTree);
	// }

	@Test
	public void testSampleParseWithoutRecursion() throws Throwable {
		PackratParser parser = new PackratParser(iiiGrammar);
		ParserTree parseTree = parser.parse("i", "iii");
		assertNotNull(parseTree);
	}

	@Test
	public void testSampleParseWithRecursion() throws Throwable {
		PackratParser parser = new PackratParser(iiiGrammar);
		ParserTree parseTree = parser.parse("iii", "iii");
		assertNotNull(parseTree);
		TreePrinter printer = new TreePrinter(System.out);
		printer.println(parseTree);
	}

	// @Test
	// public void testSampleParseWithRecursion() throws Throwable {
	// File file = new File(
	// "src/test/resources/com/puresol/uhura/grammar/TestGrammar.g");
	// assertTrue(file.exists());
	// InputStream inputStream = new FileInputStream(file);
	// try {
	// GrammarFile grammarFile = new GrammarFile(inputStream);
	// Grammar grammar = new GrammarConverter(grammarFile.getAST())
	// .getGrammar();
	// assertNotNull(grammar);
	// PackratParser parser = new PackratParser(grammar);
	// ParserTree parseTree = parser.parse("(1 + 2) * 3 + 4 * 5)",
	// "equation");
	// assertNotNull(parseTree);
	// TreePrinter printer = new TreePrinter(System.out);
	// printer.println(parseTree);
	// } finally {
	// inputStream.close();
	// }
	// }
}
