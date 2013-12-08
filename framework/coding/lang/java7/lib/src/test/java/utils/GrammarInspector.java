package utils;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.parser.impl.grammar.GrammarException;
import com.puresoltechnologies.parser.impl.parser.ParserFactory;
import com.puresoltechnologies.parser.impl.parser.ParserFactoryException;
import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammar;

public class GrammarInspector {

    public static void main(String args[]) throws IOException,
	    GrammarException, ParserFactoryException {
	Grammar grammar = JavaGrammar.getGrammar();

	ParserFactory.create(grammar).generateInspectionInformation(
		new File("grammar_inspection"));
    }
}
