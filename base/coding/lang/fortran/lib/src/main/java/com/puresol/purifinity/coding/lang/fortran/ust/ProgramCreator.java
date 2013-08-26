package com.puresol.purifinity.coding.lang.fortran.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresol.purifinity.coding.lang.fortran.metrics.HalsteadMetricImpl;
import com.puresol.purifinity.coding.lang.fortran.metrics.SLOCMetricImpl;
import com.puresol.purifinity.coding.metrics.sloc.SLOCType;
import com.puresol.purifinity.uhura.lexer.Token;
import com.puresol.purifinity.uhura.lexer.TokenMetaData;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.ust.CompilationUnit;
import com.puresol.purifinity.uhura.ust.UniversalSyntaxTree;
import com.puresol.purifinity.uhura.ust.UnspecialistProduction;
import com.puresol.purifinity.uhura.ust.terminal.Comment;
import com.puresol.purifinity.uhura.ust.terminal.Operand;
import com.puresol.purifinity.uhura.ust.terminal.Operator;
import com.puresol.purifinity.uhura.ust.terminal.WhiteSpace;

public class ProgramCreator {

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
			return new UnspecialistProduction(node.getName(), node.getName(),
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
