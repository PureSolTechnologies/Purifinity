package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.junit.Test;

import junit.framework.TestCase;

public class ProjectAnalyserTest extends TestCase {

    @Test
    public void testFortran() {
	ProjectAnalyser analyser =
		new ProjectAnalyser(new File("/home/ludwig/privat/BLAS"),
			"**/csymm.f");
	analyser.update();
	for (File file : analyser.getFiles()) {
	    Hashtable<Integer, String> tokens =
		    analyser.getLexerTokens(file);
	    ArrayList<CodeRange> ranges = analyser.getCodeRanges(file);
	    for (CodeRange range : ranges) {
		TokenStream stream = range.getTokenStream();
		for (int index = 0; index < stream.size(); index++) {
		    Token token = stream.get(index);
		    if (token.getChannel() == Token.HIDDEN_CHANNEL) {
			continue;
		    }
		    System.out.print("'");
		    System.out.print(token.getText());
		    System.out.print("'");
		    System.out.println("\t(" + token.getLine() + " / "
			    + token.getType() + " / "
			    + tokens.get(token.getType()) + ")");
		}
	    }
	}
    }
}
