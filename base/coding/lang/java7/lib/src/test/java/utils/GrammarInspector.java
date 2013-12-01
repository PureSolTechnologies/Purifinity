package utils;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammar;
import com.puresoltechnologies.purifinity.uhura.grammar.Grammar;
import com.puresoltechnologies.purifinity.uhura.grammar.GrammarException;
import com.puresoltechnologies.purifinity.uhura.parser.ParserFactory;
import com.puresoltechnologies.purifinity.uhura.parser.ParserFactoryException;

public class GrammarInspector {

    public static void main(String args[]) throws IOException,
	    GrammarException, ParserFactoryException {
	Grammar grammar = JavaGrammar.getGrammar();

	ParserFactory.create(grammar).generateInspectionInformation(
		new File("grammar_inspection"));
    }
}
