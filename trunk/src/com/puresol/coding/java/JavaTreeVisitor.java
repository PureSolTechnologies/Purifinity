/***************************************************************************
 *
 *   JavaTreeVisitor.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.apache.log4j.Logger;

import com.puresol.coding.ANTLRTreeVisitor;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.TokenContent;
import com.puresol.coding.TokenStreamScanner;
import com.puresol.coding.java.antlr.output.JavaParser;

public class JavaTreeVisitor extends ANTLRTreeVisitor {

	private static final Logger logger = Logger
			.getLogger(JavaTreeVisitor.class);

	private File file = null;
	private JavaParser parser = null;
	private ArrayList<CodeRange> codeRanges = null;
	private Hashtable<Integer, TokenContent> tokenContents = null;

	public JavaTreeVisitor(File file, JavaParser parser)
			throws RecognitionException {
		super((CommonTree) parser.compilationUnit().getTree());
		this.file = file;
		this.parser = parser;
		analyse();
	}

	/**
	 * Creates all information for later analysis. This is done only once for
	 * fast codes.
	 * 
	 * @throws RecognitionException
	 */
	private void analyse() throws RecognitionException {
		createTokenContents();
		createCodeRanges();
	}

	/**
	 * Reads all tokens and creates the TokenContent objects.
	 */
	private void createTokenContents() {
		TokenStream stream = parser.getTokenStream();
		tokenContents = new Hashtable<Integer, TokenContent>();
		for (int index = 0; index < stream.size(); index++) {
			Token token = stream.get(index);
			if (token.getChannel() == Token.HIDDEN_CHANNEL) {
				continue;
			}
			tokenContents.put(index, new JavaTokenContent(token, stream));
		}
	}

	/**
	 * Finds and creates all code ranges.
	 */
	private void createCodeRanges() {
		codeRanges = new ArrayList<CodeRange>();
		codeRanges.add(new CodeRange(file, CodeRangeType.FILE, parser
				.getSourceName(), parser.getTokenStream(), tokenContents, 0,
				parser.getTokenStream().size() - 1));
		CommonTree tree = getTree();
		findCodeRanges(tree, codeRanges);
	}

	private void findCodeRanges(Tree tree, ArrayList<CodeRange> ranges) {
		if (tree == null) {
			return;
		}
		for (int child = 0; child < tree.getChildCount(); child++) {
			Tree childTree = tree.getChild(child);
			if (childTree.getText().startsWith("CODERANGE_")) {
				logger.trace("Found '" + childTree.getText() + "'");
				CodeRange range = getCodeRange(childTree);
				ranges.add(range);
				logger.debug("range: '" + range.getName() + "'");
				logger.trace("#ranges '" + ranges.size() + "'");
			}
			findCodeRanges(childTree, ranges);
		}
	}

	/**
	 * Creates a single CodeRange for a subtree.
	 * 
	 * @param tree
	 * @return
	 */
	private CodeRange getCodeRange(Tree tree) {
		TokenStream tokenStream = parser.getTokenStream();
		int start = tree.getTokenStartIndex();
		int stop = tree.getTokenStopIndex();
		int current = start - 1;
		// find leading white spaces and comments....
		while (current > 0) {
			Token token = tokenStream.get(current);
			if (token.getChannel() != Token.HIDDEN_CHANNEL) {
				break;
			}
			if (!token.getText().trim().isEmpty()) {
				start = current;
			} else if (!token.getText().contains("\n")) {
				start = current;
			}
			current--;
		}
		while ((stop + 1 < tokenStream.size())
				&& (!tokenStream.get(stop).getText().endsWith("\n"))) {
			stop++;
		}
		CodeRangeType type = CodeRangeType.CLASS;
		if (tree.getText().endsWith("METHOD")) {
			type = CodeRangeType.METHOD;
		}
		String name = type.getName();
		TokenStreamScanner scanner = new TokenStreamScanner(tokenStream);
		if (type == CodeRangeType.CLASS) {
			int classIndex = scanner.findNextToken("class", start);
			int nameIndex = scanner.findNextToken(classIndex);
			name = tokenStream.get(nameIndex).getText();
		} else if (type == CodeRangeType.METHOD) {
			int bracketIndex = scanner.findNextToken("(", start);
			int nameIndex = scanner.findPreviousToken(bracketIndex);
			name = tokenStream.get(nameIndex).getText();
		}
		return new CodeRange(file, type, name, parser.input, tokenContents,
				start, stop);
	}

	public ArrayList<CodeRange> getCodeRanges() {
		return codeRanges;
	}
}
