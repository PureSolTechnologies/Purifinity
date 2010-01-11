package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Reads a token stream from lexer and looks for a configured structure.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Parser {

    private TokenStream tokenStream = null;

    public Parser(TokenStream tokenStream) {
	this.tokenStream = tokenStream;
    }

    public void parse(Class<? extends Part> rootPart) throws PartDoesNotMatchException {
	try {
	    Constructor<? extends Part> constructor =
		    rootPart.getConstructor(TokenStream.class, int.class);
	    Part rootPartInstance =
		    (Part) constructor.newInstance(tokenStream, 0);
	    rootPartInstance.scan();
	} catch (SecurityException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (NoSuchMethodException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvocationTargetException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
