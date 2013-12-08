package utils;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.parser.impl.grammar.GrammarException;
import com.puresoltechnologies.parser.impl.parser.ParserFactory;
import com.puresoltechnologies.parser.impl.parser.ParserFactoryException;
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
