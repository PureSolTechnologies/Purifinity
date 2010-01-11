package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractPart implements Part {

    private TokenStream tokenStream = null;
    private int startPos;
    private int currentPos;

    public AbstractPart(TokenStream tokenStream, int startPos) {
	this.tokenStream = tokenStream;
	this.startPos = startPos;
	this.currentPos = startPos;
    }

    protected int getStartPosition() {
	return startPos;
    }

    protected int getCurrentPosition() {
	return currentPos;
    }

    public int getNumberOfTokens() {
	return currentPos - startPos;
    }

    protected Token getCurrentToken() {
	return tokenStream.get(currentPos);
    }

    private void moveForward(int steps) {
	currentPos += steps;
	while (getCurrentToken().getPublicity() == TokenPublicity.HIDDEN) {
	    currentPos++;
	}
    }

    protected void processToken(Class<? extends TokenDefinition> definition)
	    throws PartDoesNotMatchException {
	if (!getCurrentToken().getDefinition().equals(definition)) {
	    throw new PartDoesNotMatchException();
	}
	moveForward(1);
    }

    protected boolean isToken(Class<? extends TokenDefinition> definition) {
	return getCurrentToken().getDefinition().equals(definition);
    }

    public void processPart(Class<? extends Part> part)
	    throws PartDoesNotMatchException {
	try {
	    Constructor<?> constructor =
		    part.getConstructor(TokenStream.class, int.class);
	    Part partInstance =
		    (Part) constructor.newInstance(tokenStream,
			    getCurrentPosition());
	    partInstance.scan();
	    moveForward(partInstance.getNumberOfTokens());
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

    protected boolean tryPart(Class<? extends Part> part) {
	try {
	    Constructor<?> constructor =
		    part.getConstructor(TokenStream.class, int.class);
	    Part partInstance =
		    (Part) constructor.newInstance(tokenStream,
			    getCurrentPosition());
	    partInstance.scan();
	    currentPos += partInstance.getNumberOfTokens();
	    return true;
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
	} catch (PartDoesNotMatchException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return false;
    }
}
