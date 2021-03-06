package com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.parser.ParseTreeNode;
import com.puresoltechnologies.parsers.parser.packrat.PackratParser;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.server.plugin.c11.grammar.C11Grammar;
import com.puresoltechnologies.trees.SearchVisitor;
import com.puresoltechnologies.trees.TreeSearchCriterion;
import com.puresoltechnologies.trees.TreeWalker;

public class C11PreprocessorParserTest {

	private ParseTreeNode checkParser(String... lines) throws Exception {
		SourceCode sourceCode = SourceCode.fromStringArray(lines);

		C11PreprocessorParser parser = new C11PreprocessorParser();
		ParseTreeNode ast = parser.parse(sourceCode);

		assertNotNull(ast);
		return ast;
	}

	private void checkRule(String productionName, String... lines)
			throws Exception {
		Grammar production = C11Grammar.getGrammar()
				.createWithNewStartProduction(productionName);
		C11PreprocessorParser.setLineTerminatorToVisible(production);
		System.out.println(production.toString());
		PackratParser parser = new PackratParser(production);
		ParseTreeNode ast = parser.parse(SourceCode.fromStringArray(lines));
		assertContainsNode(productionName, ast);
	}

	private void assertContainsNode(final String nodeName, ParseTreeNode tree) {
		TreeSearchCriterion<ParseTreeNode> criterion = new TreeSearchCriterion<ParseTreeNode>() {

			@Override
			public boolean accepted(ParseTreeNode node) {
				if (node.getName().equals(nodeName)) {
					return true;
				}
				return false;
			}
		};
		SearchVisitor<ParseTreeNode> searchVisitor = new SearchVisitor<ParseTreeNode>(
				criterion);
		TreeWalker.walk(searchVisitor, tree);
		assertEquals(1, searchVisitor.getSearchResult().size());
	}

	@Test
	public void testParsingEmptyFile() throws Exception {
		checkParser("");
	}

	@Test
	public void testParsingSimpleInclude1() throws Exception {
		checkParser("#include \"testfile.h\"\n");
	}

	@Test
	public void testParsingSimpleInclude2() throws Exception {
		checkParser("#include <testfile.h>\n");
	}

	@Test
	public void testError() throws Exception {
		checkParser("#error \"This is an error message!\"\n");
	}

	@Test
	public void testInclude() throws Exception {
		checkParser("#include <include.txt>\n");
	}

	@Test
	public void testLocalInclude() throws Exception {
		checkParser("#include \"include.txt\"\n");
	}

	@Test
	public void testDefineMacroOnlyWithName() throws Exception {
		checkParser("#define NAME\n");
	}

	@Test
	public void testDefineObjectLikeMacroWithOneReplacement() throws Exception {
		checkParser("#define NAME replacement\n");
	}

	@Test
	public void testDefineObjectLikeMacroWithMultipleReplacements()
			throws Exception {
		checkParser("#define NAME replacement1 replacement2 replacement3 replacement4\n");
	}

	@Test
	public void testDefineFunctionLikeMacroWithOneParameterAndWithOneReplacement()
			throws Exception {
		checkParser("#define NAME(x) replacement1\n");
	}

	@Test
	public void testDefineFunctionLikeMacroWithOneParameterAndWithMultipleReplacements()
			throws Exception {
		checkParser("#define NAME(x) replacement1 replacement2 replacement3 replacement4\n");
	}

	@Test
	public void testDefineFunctionLikeMacroWithMultipleParametersAndWithOneReplacement()
			throws Exception {
		checkParser("#define NAME(x, y, z) replacement1\n");
	}

	@Test
	public void testDefineFunctionLikeMacroWithMultipleParametersAndWithMultipleReplacements()
			throws Exception {
		checkParser("#define NAME(x, y, z) replacement1 replacement2 replacement3 replacement4\n");
	}

	@Test
	public void testDefineFunctionLikeMacroWithOptionalParametersOnly()
			throws Exception {
		checkParser("#define NAME(...) replacement1\n");
	}

	@Test
	public void testDefineFunctionLikeMacroWithOneParameterAndOptionalParameters()
			throws Exception {
		checkParser("#define NAME(x, ...) replacement1\n");
	}

	@Test
	public void testDefineFunctionLikeMacroWithMultipleParametersAndOptionalParameters()
			throws Exception {
		checkParser("#define NAME(x, y, z, ...) replacement1\n");
	}

	@Test
	public void testDefineFunctionLikeMacroWithMultipleParametersOptionalParametersAndComplexReplacement()
			throws Exception {
		checkParser("#define NAME(x, y, z, ...) fprintf(\"Hello x, y, z\"\n");
	}

	@Test
	public void testIfGroup() throws Exception {
		checkRule("if-group", "#ifdef TEST\n");
		checkRule("if-group", "#ifndef TEST\n");
		checkRule("if-group", "#if (a == 3)\n");
		checkRule("if-group", "#ifdef TEST\n", "line\n");
		checkRule("if-group", "#ifndef TEST\n", "line\n");
		checkRule("if-group", "#if (a == 3)\n", "line\n");
	}

	@Test
	public void testElIfGroup() throws Exception {
		checkRule("elif-group", "#elif (a==3)\n");
		checkRule("elif-group", "#elif (a==3)\n", "line\n");
	}

	@Test
	public void testElseGroup() throws Exception {
		checkRule("else-group", "#else\n");
		checkRule("else-group", "#else\n", "line\n");
	}

	@Test
	public void testEndIfLine() throws Exception {
		checkRule("endif-line", "#endif\n");
	}

	@Test
	public void testIfSection() throws Exception {
		checkParser("#ifdef TEST\n", "#endif\n");
	}

	@Test
	public void testSimpleEmptyIfDef() throws Exception {
		ParseTreeNode tree = checkParser("#ifdef TEST\n", "#endif\n");
		assertContainsNode("if-section", tree);
	}

	@Test
	public void testSimpleIfDef() throws Exception {
		checkParser("#ifdef TEST\n", "Hallo\n", "#endif\n");
	}

	@Test
	public void testSimpleIfDefElse() throws Exception {
		checkParser("#ifdef TEST\n", "Hallo\n", "#else\n", "#endif\n");
	}

	@Test
	public void testSimpleEmptyIfNDef() throws Exception {
		checkParser("#ifndef TEST\n", "#endif\n");
	}

	@Test
	public void testSimpleIfNDef() throws Exception {
		checkParser("#ifndef TEST\n", "Hallo\n", "#endif\n");
	}

	@Test
	public void testSimpleIfNDefElse() throws Exception {
		checkParser("#ifndef TEST\n", "Hallo\n", "#else\n", "#endif\n");
	}

}
