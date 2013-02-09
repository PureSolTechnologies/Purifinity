package utils;

import java.io.File;
import java.io.IOException;

import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserFactoryException;

public class GrammarInspector {

    public static void main(String args[]) throws IOException,
	    GrammarException, ParserFactoryException {
	Grammar grammar = JavaGrammar.getGrammar();

	ParserFactory.create(grammar).generateInspectionInformation(
		new File("grammar_inspection"));
    }
}
