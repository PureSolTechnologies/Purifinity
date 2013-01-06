package com.puresol.uhura.parser.packrat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.trees.TreePrinter;
import com.puresol.trees.TreeVisitor;
import com.puresol.trees.TreeWalker;
import com.puresol.trees.WalkingAction;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarConverter;
import com.puresol.uhura.grammar.GrammarFile;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.grammar.production.NonTerminal;
import com.puresol.uhura.grammar.production.Production;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.source.FixedCodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.utils.IntrospectionUtilities;

public class PackratParserTest {

    private static Grammar directRecursionGrammar;
    private static Grammar directRecursionGrammarZero;
    private static Grammar indirectRecursionGrammar;
    private static Grammar nestedRecursionsGrammar;
    private static Grammar testGrammar;

    private ParserTree parseText(Grammar grammar, String text) throws Exception {
	PackratParser parser = new PackratParser(grammar);
	FixedCodeLocation codeLocation = new FixedCodeLocation(text);
	SourceCode sourceCode = codeLocation.load();
	ParserTree parseTree = parser.parse(sourceCode);
	assertNotNull(parseTree);
	checkForCorrectParents(parseTree);
	TreePrinter printer = new TreePrinter(System.out);
	printer.println(parseTree);
	return parseTree;
    }

    /**
     * <p>
     * <b>This test is essential! Do not delete without clarification of the
     * original author!</b>
     * </p>
     * <p>
     * This test was added after finding issues of parent entries in child
     * nodes. During packrat parsing memoization is used to speed up the parsing
     * process up to linear speed behavior. For that sub results are stored and
     * reused. During the reuse a sub tree is added several time to different
     * nodes and therefore the parent is set several times. The final result is
     * not necessarily the last parent entry! For that a normalization step was
     * introduced.
     * </p>
     * <p>
     * This test checks now for the correct set parents in child nodes. If the
     * normalization is disabled, this test in this test class shows also the
     * issue.
     * </p>
     * 
     * @param parseTree
     * @throws Exception
     */
    private void checkForCorrectParents(ParserTree parseTree) throws Exception {
	final Field parentField = ParserTree.class.getDeclaredField("parent");
	parentField.setAccessible(true);
	TreeVisitor<ParserTree> visitor = new TreeVisitor<ParserTree>() {

	    @Override
	    public WalkingAction visit(ParserTree tree) {
		try {
		    for (ParserTree child : tree.getChildren()) {
			assertEquals("Parent entry in child is not correct!",
				tree, parentField.get(child));
		    }
		} catch (IllegalArgumentException e) {
		    throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
		    throw new IllegalStateException(e);
		}
		return WalkingAction.PROCEED;
	    }
	};
	new TreeWalker<ParserTree>(parseTree).walk(visitor);
	parentField.setAccessible(false);
    }

    @BeforeClass
    public static void initialize() throws Throwable {
	directRecursionGrammar = readGrammar("/com/puresol/uhura/grammar/DirectRecursiveTestGrammar.g");
	directRecursionGrammarZero = readGrammar("/com/puresol/uhura/grammar/DirectRecursiveTestGrammarZero.g");
	indirectRecursionGrammar = readGrammar("/com/puresol/uhura/grammar/IndirectRecursiveTestGrammar.g");
	nestedRecursionsGrammar = readGrammar("/com/puresol/uhura/grammar/NestedRecursionTestGrammar.g");
	testGrammar = readGrammar("/com/puresol/uhura/grammar/TestGrammar.g");
    }

    private static Grammar readGrammar(String resource) throws Throwable {
	InputStream inStream = PackratParserTest.class
		.getResourceAsStream(resource);
	assertNotNull(inStream);
	try {
	    GrammarFile file = new GrammarFile(inStream);
	    try {
		Grammar grammar = new GrammarConverter(file.getAST())
			.getGrammar();
		assertNotNull(grammar);
		return grammar;
	    } finally {
		file.close();
	    }
	} finally {
	    inStream.close();
	}
    }

    @Test
    public void testInstance() {
	assertNotNull(new PackratParser(
		TestGrammars.getLLGrammarFromDragonBook()));
    }

    @Test
    public void testPatternAndMatcherForCertainBehavior() {
	Pattern pattern = Pattern.compile("^\t");
	Matcher matcher = pattern.matcher(" \t ".substring(1));
	assertTrue(matcher.find());
    }

    @Test
    public void testProcessWhitespaces() throws Throwable {

	Grammar grammar = TestGrammars.getLLGrammarFromDragonBook();
	TokenDefinitionSet tokenDefinitions = grammar.getTokenDefinitions();
	tokenDefinitions.addDefinition(new TokenDefinition("Space", " ",
		Visibility.IGNORED));
	tokenDefinitions.addDefinition(new TokenDefinition("Tab", "\\t",
		Visibility.IGNORED));
	PackratParser parser = new PackratParser(grammar);

	IntrospectionUtilities.setField(parser, "text", " \t ");
	IntrospectionUtilities.setField(parser, "sourceCode",
		new FixedCodeLocation(" \t ").load());
	/*
	 * process some white spaces...
	 */
	ParserTree parserTree = new ParserTree("ROOT");
	MemoEntry memoEntry = parser.processIgnoredTokens(parserTree, 0, 1);

	assertEquals(3, memoEntry.getDeltaPosition());

	assertEquals(3, parserTree.getChildren().size());
	Token token0 = parserTree.getChildren().get(0).getToken();
	Token token1 = parserTree.getChildren().get(1).getToken();
	Token token2 = parserTree.getChildren().get(2).getToken();

	assertEquals("Space", token0.getName());
	assertEquals("Tab", token1.getName());
	assertEquals("Space", token2.getName());

	assertEquals(0, token0.getMetaData().getPos());
	assertEquals(1, token1.getMetaData().getPos());
	assertEquals(2, token2.getMetaData().getPos());

	assertEquals(1, token0.getMetaData().getLine());
	assertEquals(1, token1.getMetaData().getLine());
	assertEquals(1, token2.getMetaData().getLine());
    }

    @Test
    public void testSampleParse() throws Throwable {
	Grammar grammar = TestGrammars.getLLGrammarFromDragonBook();
	assertNotNull(grammar);
	Production production = new Production("_START_");
	production.addConstruction(new NonTerminal("E"));
	grammar.getProductions().add(production);

	parseText(grammar, "(1+2)*3+4*5");
    }

    @Test
    public void testDirectRecursion() throws Throwable {
	parseText(directRecursionGrammar, "i");
	parseText(directRecursionGrammar, "ii");
	parseText(directRecursionGrammar, "iii");
    }

    @Test
    public void testDirectRecursionWithEmpty() throws Throwable {
	parseText(directRecursionGrammarZero, "i");
	parseText(directRecursionGrammarZero, "ii");
	parseText(directRecursionGrammarZero, "iii");
    }

    @Test
    public void testIndirectRecursion() throws Throwable {
	parseText(indirectRecursionGrammar, "i");
	parseText(indirectRecursionGrammar, "ii");
	parseText(indirectRecursionGrammar, "iii");
    }

    @Test
    public void testNestedRecursions() throws Throwable {
	parseText(nestedRecursionsGrammar, "i");
	parseText(nestedRecursionsGrammar, "ii");
	parseText(nestedRecursionsGrammar, "iii");
	parseText(nestedRecursionsGrammar, "j");
	parseText(nestedRecursionsGrammar, "jj");
	parseText(nestedRecursionsGrammar, "jjj");
	parseText(nestedRecursionsGrammar, "k");
	parseText(nestedRecursionsGrammar, "kk");
	parseText(nestedRecursionsGrammar, "kkk");

	parseText(nestedRecursionsGrammar, "ijk");
	parseText(nestedRecursionsGrammar, "ijkijk");
	parseText(nestedRecursionsGrammar, "iijjkkiijjkk");
    }

    @Test
    public void testEquation() throws Throwable {
	parseText(testGrammar, "1");
	parseText(testGrammar, "(1)");
	parseText(testGrammar, "1+2");
	parseText(testGrammar, "(1+2)");
	parseText(testGrammar, "1*2");
	parseText(testGrammar, "(1*2)");
    }

    @Test
    public void testEquation2() throws Throwable {
	parseText(testGrammar, "1 * 2 + 3 * 4 + 5 * (6 + 7 * 	 (8 + 9))");
	parseText(testGrammar, "1 - 2 - 3");
    }
}
