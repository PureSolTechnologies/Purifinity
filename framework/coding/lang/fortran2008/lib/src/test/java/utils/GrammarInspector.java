package utils;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.parser.ParserFactory;
import com.puresoltechnologies.parsers.impl.parser.ParserFactoryException;
import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammar;

public class GrammarInspector {

    public static void main(String args[]) {
	try {
	    Grammar grammar = FortranGrammar.getInstance();
	    // grammar = grammar
	    // .createWithNewStartProduction("specification-part");

	    ParserFactory.create(grammar).generateInspectionInformation(
		    new File("grammar_inspection"));

	} catch (IOException e) {
	    e.printStackTrace();
	} catch (GrammarException e) {
	    e.printStackTrace();
	} catch (ParserFactoryException e) {
	    e.printStackTrace();
	}
    }
}
