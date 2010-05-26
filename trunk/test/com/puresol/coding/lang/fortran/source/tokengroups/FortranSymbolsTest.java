package com.puresol.coding.lang.fortran.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import javax.swingx.config.ExtentedPackage;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.lang.fortran.Fortran;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.TokenDefinition;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public class FortranSymbolsTest {

    private static final ArrayList<Class<?>> symbols;
    static {
	symbols = new ArrayList<Class<?>>();
	try {
	    for (Class<?> clazz : ExtentedPackage
		    .getClasses("com.puresol.coding.lang.fortran.source.symbols")) {
		symbols.add(clazz);
	    }
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    @Before
    public void testSymbolsVector() {
	Assert.assertTrue(symbols.size() > 0);
    }

    @Test
    public void testSymbolsTokenInterfaces() {
	for (Class<?> clazz : symbols) {
	    if (clazz.getName().endsWith("Test")) {
		continue;
	    }
	    if (!SourceTokenDefinition.class.isAssignableFrom(clazz)) {
		Assert.fail(clazz.getName() + " has not the right interface!");
	    }
	}
    }

    @Test
    public void testSymbolsCompleteness() {
	try {
	    List<TokenDefinition> tokens = Instances.createInstanceList(
		    TokenDefinition.class, Fortran.getInstance().getSymbols());
	    for (TokenDefinition definition : tokens) {
		if (!symbols.contains(definition.getClass())) {
		    Assert.fail(definition.getClass()
			    + " is not included in FortranSymbols!");
		}
	    }
	    for (Class<?> clazz : symbols) {
		if (clazz.getName().endsWith("Test")) {
		    continue;
		}
		if (clazz.getName().endsWith("LineLead")) {
		    continue;
		}
		boolean found = false;
		for (TokenDefinition definition : tokens) {
		    if (definition.getClass().equals(clazz)) {
			found = true;
			break;
		    }
		}
		if (!found) {
		    Assert.fail(clazz.getName()
			    + " is not registered in FortranSymbols!");
		}
	    }
	} catch (ClassInstantiationException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }
}
