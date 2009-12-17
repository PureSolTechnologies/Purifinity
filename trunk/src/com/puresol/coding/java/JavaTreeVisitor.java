package com.puresol.coding.java;

import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import com.puresol.coding.ANTLRTreeVisitor;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.TokenContent;
import com.puresol.coding.java.antlr.output.JavaParser;

public class JavaTreeVisitor extends ANTLRTreeVisitor {

	private JavaParser parser = null;
	private ArrayList<CodeRange> codeRanges = null;
	private Hashtable<Integer, TokenContent> tokenContents = null;

	public JavaTreeVisitor(JavaParser parser) throws RecognitionException {
		super((CommonTree) parser.compilationUnit().getTree());
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
		codeRanges.add(new CodeRange(CodeRangeType.FILE,
				parser.getSourceName(), parser.getTokenStream(), tokenContents,
				0, parser.getTokenStream().size() - 1));
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
				CodeRange range = getCodeRange(childTree);
				ranges.add(range);
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
		return new CodeRange(type, type.getName(), parser.input, tokenContents,
				start, stop);
	}

	public ArrayList<CodeRange> getCodeRanges() {
		return codeRanges;
	}
}
