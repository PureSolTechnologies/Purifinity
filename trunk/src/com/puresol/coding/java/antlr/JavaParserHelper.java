package com.puresol.coding.java.antlr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.antlr.runtime.Token;
import org.apache.log4j.Logger;

import com.puresol.coding.java.antlr.output.JavaParser;
import com.puresol.exceptions.StrangeSituationException;

/**
 * This class is a helper class for JavaParser to collect all information needed
 * for method tracking, Halstead and McCabe metric calculation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaParserHelper {

	private static final Logger logger = Logger
			.getLogger(JavaParserHelper.class);

	private Hashtable<Integer, TokenContent> tokenContents = new Hashtable<Integer, TokenContent>();
	private ArrayList<JavaRange> methods = new ArrayList<JavaRange>();
	private JavaParser javaParser = null;

	public JavaParserHelper(JavaParser javaParser) {
		this.javaParser = javaParser;
	}

	private int getCurrentPosition() {
		return javaParser.input.index();
	}

	public void registerOperator(String operator, int cyclomaticNumber) {
		TokenContent tokenContent = new TokenContent(getCurrentPosition());
		tokenContent.setOperator(operator);
		tokenContent.setCyclomaticNumber(cyclomaticNumber);
		tokenContents.put(getCurrentPosition(), tokenContent);
		if (logger.isTraceEnabled()) {
			logger.trace("Register operator: '" + operator + "'");
		}
	}

	public void registerOperator(String operator) {
		TokenContent tokenContent = new TokenContent(getCurrentPosition());
		tokenContent.setOperator(operator);
		tokenContents.put(getCurrentPosition(), tokenContent);
		if (logger.isTraceEnabled()) {
			logger.trace("Register operator: '" + operator + "'");
		}
	}

	public void registerOperant(String operant) {
		TokenContent tokenContent = new TokenContent(getCurrentPosition());
		tokenContent.setOperant(operant);
		tokenContents.put(getCurrentPosition(), tokenContent);
		if (logger.isTraceEnabled()) {
			logger.trace("Register operant: '" + operant + "'");
		}
	}

	public void registerRange(String type, String name, String text, int start, int stop) {
		JavaRange method = new JavaRange(type, name, text);
		for (int index = start; index <= stop; index++) {
			if (tokenContents.containsKey(index)) {
				TokenContent content = tokenContents.get(index);
				if (!content.getOperant().isEmpty()) {
					method.addOperant(content.getOperant());
				}
				if (!content.getOperator().isEmpty()) {
					method.addOperator(content.getOperator());
				}
				method.addCyclomaticNumber(content.getCyclomaticNumber());
			} else {
				Token token = javaParser.input.get(index);
				if (token.getChannel() != 99) {
					logger
							.warn("Token '"
									+ token.getText()
									+ "' was not categorized to Operator or Operant! (line;"
									+ token.getLine() + ")");
				}
			}
		}
		methods.add(method);
	}

	public List<JavaRange> getMethods() {
		return methods;
	}
}
