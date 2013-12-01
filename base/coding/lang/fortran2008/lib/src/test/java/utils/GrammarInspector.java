package utils;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammar;
import com.puresoltechnologies.purifinity.uhura.grammar.Grammar;
import com.puresoltechnologies.purifinity.uhura.grammar.GrammarException;
import com.puresoltechnologies.purifinity.uhura.parser.ParserFactory;
import com.puresoltechnologies.purifinity.uhura.parser.ParserFactoryException;

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
