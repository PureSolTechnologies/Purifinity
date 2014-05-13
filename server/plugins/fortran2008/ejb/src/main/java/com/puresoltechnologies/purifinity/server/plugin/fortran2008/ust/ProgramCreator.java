package com.puresoltechnologies.purifinity.server.plugin.fortran2008.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.parsers.lexer.Token;
import com.puresoltechnologies.parsers.lexer.TokenMetaData;
import com.puresoltechnologies.parsers.parser.ParserTree;
import com.puresoltechnologies.parsers.ust.CompilationUnit;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.UnspecialistProduction;
import com.puresoltechnologies.parsers.ust.terminal.Comment;
import com.puresoltechnologies.parsers.ust.terminal.Operand;
import com.puresoltechnologies.parsers.ust.terminal.Operator;
import com.puresoltechnologies.parsers.ust.terminal.WhiteSpace;
import com.puresoltechnologies.purifinity.server.metrics.spi.sloc.SLOCType;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.metrics.HalsteadMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.metrics.SLOCMetricImpl;

public class ProgramCreator {

	private static final SLOCMetricImpl slocMetricImpl = new SLOCMetricImpl();
	private static final HalsteadMetricImpl halsteadMetricImpl = new HalsteadMetricImpl();

	public static CompilationUnit create(ParserTree parserTree) {
		List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
		for (ParserTree child : parserTree.getChildren()) {
			UniversalSyntaxTree childNode = createNode(child);
			if (childNode != null) {
				ustChildren.add(childNode);
			}
		}
		return new CompilationUnit(parserTree.getName(), parserTree.getText(),
				ustChildren);
	}

	private static UniversalSyntaxTree createNode(ParserTree node) {
		Token token = node.getToken();
		if (token == null) {
			List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
			for (ParserTree child : node.getChildren()) {
				UniversalSyntaxTree childNode = createNode(child);
				if (childNode != null) {
					ustChildren.add(childNode);
				}
			}
			if (ustChildren.size() > 0) {
				return new UnspecialistProduction(node.getName(),
						node.getName(), ustChildren);
			} else {
				return null;
			}
		} else {
			TokenMetaData metaData = token.getMetaData();
			SLOCType type = slocMetricImpl.getTypeByName(token.getName());
			switch (type) {
			case COMMENT:
				return new Comment(token.getName(), token.getText(),
						metaData.getLine(), metaData.getColumn());
			case BLANK:
				return new WhiteSpace(token.getName(), token.getText(),
						metaData.getLine(), metaData.getColumn());
			case PHYSICAL:
			case PRODUCTIVE:
				if (halsteadMetricImpl.isOperator(token.getName())) {
					return new Operator(token.getName(), token.getText(),
							metaData.getLine(), metaData.getColumn());
				} else {
					return new Operand(token.getName(), token.getText(),
							metaData.getLine(), metaData.getColumn());
				}
			default:
				throw new IllegalStateException("The type '" + type.name()
						+ "' is unknown for Java.");
			}
		}
	}
}
