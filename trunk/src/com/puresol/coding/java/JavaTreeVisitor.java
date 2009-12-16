package com.puresol.coding.java;

import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.TokenContent;
import com.puresol.coding.java.antlr.output.JavaParser;

public class JavaTreeVisitor {

	private static final Logger logger = Logger
			.getLogger(JavaTreeVisitor.class);

	private JavaParser parser = null;

	public JavaTreeVisitor(JavaParser parser) throws RecognitionException {
		this.parser = parser;
	}

	private void findCodeRanges(Tree tree, ArrayList<CodeRange> ranges) {
		for (int child = 0; child < tree.getChildCount(); child++) {
			Tree childTree = tree.getChild(child);
			if (childTree.getText().startsWith("CODERANGE_")) {
				CodeRange range = getCodeRange(childTree);
				ranges.add(range);
			}
			findCodeRanges(childTree, ranges);
		}
	}

	public ArrayList<CodeRange> getCodeRanges() {
		try {
			ArrayList<CodeRange> ranges = new ArrayList<CodeRange>();
			JavaParser.compilationUnit_return result = parser.compilationUnit();
			CommonTree tree = (CommonTree) result.getTree();
			findCodeRanges(tree, ranges);
			return ranges;
		} catch (RecognitionException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	private CodeRange getCodeRange(Tree tree) {
		TokenStream tokenStream = parser.getTokenStream();
		Hashtable<Integer, TokenContent> tokenContents = new Hashtable<Integer, TokenContent>();
		int start = tree.getTokenStartIndex();
		int stop = tree.getTokenStopIndex();
		int current = start - 1;
		while (current > 0) {
			Token token = tokenStream.get(current);
			if (token.getChannel() != 99) {
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
		String text = "";

		for (int index = start; index <= stop; index++) {
			text += tokenStream.get(index).getText();
		}

		CodeRangeType type = CodeRangeType.CLASS;
		if (tree.getText().endsWith("METHOD")) {
			type = CodeRangeType.METHOD;
		}
		CodeRange range = new CodeRange(type, type.getName(), text,
				parser.input, tokenContents, start, stop);
		return range;
	}
}
