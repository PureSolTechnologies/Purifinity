package com.puresoltechnologies.purifinity.coding.lang.java7.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.purifinity.coding.lang.java7.metrics.HalsteadMetricImpl;
import com.puresoltechnologies.purifinity.coding.lang.java7.metrics.SLOCMetricImpl;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCType;
import com.puresoltechnologies.purifinity.uhura.lexer.Token;
import com.puresoltechnologies.purifinity.uhura.lexer.TokenMetaData;
import com.puresoltechnologies.purifinity.uhura.parser.ParserTree;
import com.puresoltechnologies.purifinity.uhura.ust.CompilationUnit;
import com.puresoltechnologies.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresoltechnologies.purifinity.uhura.ust.UnspecialistProduction;
import com.puresoltechnologies.purifinity.uhura.ust.terminal.Comment;
import com.puresoltechnologies.purifinity.uhura.ust.terminal.Operand;
import com.puresoltechnologies.purifinity.uhura.ust.terminal.Operator;
import com.puresoltechnologies.purifinity.uhura.ust.terminal.WhiteSpace;

public class CompilationUnitCreator {

	private static final SLOCMetricImpl slocMetricImpl = new SLOCMetricImpl();
	private static final HalsteadMetricImpl halsteadMetricImpl = new HalsteadMetricImpl();

	public static CompilationUnit create(ParserTree parserTree) {
		List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
		for (ParserTree child : parserTree.getChildren()) {
			ustChildren.add(createNode(child));
		}
		return new CompilationUnit(parserTree.getName(), parserTree.getText(),
				ustChildren);
	}

	private static UniversalSyntaxTree createNode(ParserTree node) {
		Token token = node.getToken();
		if (token == null) {
			List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
			for (ParserTree child : node.getChildren()) {
				ustChildren.add(createNode(child));
			}
			return new UnspecialistProduction(node.getName(), node.getText(),
					ustChildren);
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
