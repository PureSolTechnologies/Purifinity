package utils;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.parser.ParserFactory;
import com.puresoltechnologies.parsers.impl.parser.ParserFactoryException;
import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammar;

public class GrammarInspector {

    public static void main(String args[]) throws IOException,
	    GrammarException, ParserFactoryException {
	Grammar grammar = JavaGrammar.getGrammar();

	ParserFactory.create(grammar).generateInspectionInformation(
		new File("grammar_inspection"));
    }
}
