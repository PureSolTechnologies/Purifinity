package com.puresoltechnologies.purifinity.server.plugin.java7.ust;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.parsers.lexer.Token;
import com.puresoltechnologies.parsers.lexer.TokenMetaData;
import com.puresoltechnologies.parsers.parser.ParseTreeNode;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.CompilationUnit;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.UnspecialistProduction;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.parsers.ust.terminal.Comment;
import com.puresoltechnologies.parsers.ust.terminal.Operand;
import com.puresoltechnologies.parsers.ust.terminal.Operator;
import com.puresoltechnologies.parsers.ust.terminal.WhiteSpace;
import com.puresoltechnologies.purifinity.analysis.domain.CodeDepthLabels;
import com.puresoltechnologies.purifinity.analysis.domain.HalsteadLabels;
import com.puresoltechnologies.purifinity.analysis.domain.HalsteadSymbol;
import com.puresoltechnologies.purifinity.analysis.domain.McCabeLabels;
import com.puresoltechnologies.purifinity.analysis.domain.SLOCType;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.CodeDepthMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.HalsteadMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.McCabeMetricImpl;
import com.puresoltechnologies.purifinity.server.plugin.java7.metrics.SLOCMetricImpl;

public class CompilationUnitCreator {

    private static final SLOCMetricImpl slocMetricImpl = new SLOCMetricImpl();
    private static final HalsteadMetricImpl halsteadMetricImpl = new HalsteadMetricImpl();
    private static final CodeDepthMetricImpl codeDepthMetricImpl = new CodeDepthMetricImpl();
    private static final McCabeMetricImpl mcCabeMetricImpl = new McCabeMetricImpl();

    public static CompilationUnit create(ParseTreeNode parseTree) {
	List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
	for (ParseTreeNode child : parseTree.getChildren()) {
	    ustChildren.add(createNode(child));
	}
	CompilationUnit compilationUnit = new CompilationUnit(
		parseTree.getName(), parseTree.getText(), ustChildren);
	addLabels(compilationUnit);
	return compilationUnit;
    }

    private static UniversalSyntaxTree createNode(ParseTreeNode node) {
	Token token = node.getToken();
	if (token == null) {
	    List<UniversalSyntaxTree> ustChildren = new ArrayList<>();
	    for (ParseTreeNode child : node.getChildren()) {
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

    private static void addLabels(UniversalSyntaxTree ust) {
	if (AbstractTerminal.class.isAssignableFrom(ust.getClass())) {
	    AbstractTerminal abstractTerminal = (AbstractTerminal) ust;
	    SLOCType type = slocMetricImpl.getType(abstractTerminal);
	    ust.addLabel(type.getLabel());
	    HalsteadSymbol halsteadResult = halsteadMetricImpl
		    .getHalsteadResult(abstractTerminal);
	    if (halsteadResult.isOperator()) {
		ust.addLabel(HalsteadLabels.OPERATOR);
	    } else {
		ust.addLabel(HalsteadLabels.OPERAND);
	    }
	    if (halsteadResult.isRelevant()) {
		ust.addLabel(HalsteadLabels.RELEVANT);
		ust.setProperty(HalsteadLabels.SYMBOL,
			halsteadResult.getSymbol());
	    }
	}
	if (codeDepthMetricImpl.isCascading(ust)) {
	    ust.addLabel(CodeDepthLabels.CASCADING);
	}
	if (AbstractProduction.class.isAssignableFrom(ust.getClass())) {
	    int vG = mcCabeMetricImpl
		    .increasesCyclomaticComplexityBy((AbstractProduction) ust);
	    ust.setProperty(McCabeLabels.VG, vG);
	}
	for (UniversalSyntaxTree child : ust.getChildren()) {
	    addLabels(child);
	}
    }
}
