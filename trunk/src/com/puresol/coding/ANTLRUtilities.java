package com.puresol.coding;

import java.io.PrintStream;
import java.util.List;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class ANTLRUtilities {

	public static void printTokenStream(CommonTokenStream stream) {
		printTokenStream(stream, System.out);
	}

	@SuppressWarnings("unchecked")
	public static void printTokenStream(CommonTokenStream stream,
			PrintStream out) {
		for (Token token : (List<Token>) stream.getTokens()) {
			out.println(token.getText());
		}
	}

	public static void printTokenStream(TokenStream stream) {
		printTokenStream(stream, System.out);
	}

	public static void printTokenStream(TokenStream stream, PrintStream out) {
		for (int index = 0; index < stream.size(); index++) {
			out.println(stream.get(index).getText());
		}
	}

}
