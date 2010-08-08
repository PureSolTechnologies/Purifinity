package com.puresol.coding.lang.fortran.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import javax.swingx.config.ExtentedPackage;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.puresol.coding.lang.fortran.Fortran;
import com.puresol.coding.tokentypes.AbstractSourceTokenDefinition;
import com.puresol.coding.tokentypes.SourceTokenDefinition;
import com.puresol.parser.tokens.TokenDefinition;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public class FortranKeywordsTest {

	private static final ArrayList<Class<?>> keywords;
	static {
		keywords = new ArrayList<Class<?>>();
		try {
			for (Class<?> clazz : ExtentedPackage
					.getClasses("com.puresol.coding.lang.fortran.source.keywords")) {
				keywords.add(clazz);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void testKeywordsVector() {
		Assert.assertTrue(keywords.size() > 0);
	}

	@Test
	public void testKeywordsTokenInterfaces() {
		for (Class<?> clazz : keywords) {
			if (clazz.getName().endsWith("Test")) {
				continue;
			}
			if (!SourceTokenDefinition.class.isAssignableFrom(clazz)) {
				Assert.fail(clazz.getName() + " has not the right interface!");
			}
		}

	}

	@Test
	public void testKeywordsCompleteness() {
		try {
			List<TokenDefinition> tokens = Instances.createInstanceList(
					TokenDefinition.class, Fortran.getInstance().getKeywords());
			for (TokenDefinition definition : tokens) {
				if (!keywords.contains(definition.getClass())) {
					Assert.fail(definition.getClass()
							+ " is not included in FortranKeywords!");
				}
				AbstractSourceTokenDefinition sourceToken = (AbstractSourceTokenDefinition) definition;
				// if
				// (!sourceToken.getLookAheadPatternString().equals("(?!\\w)"))
				// {
				// Assert.fail(definition.getClass().getName()
				// + " does not have a look ahead '(?!\\w)' registered!");
				// }
				if (sourceToken.getPatternString().endsWith(
						sourceToken.getLookAheadPatternString())
						&& (!sourceToken.getLookAheadPatternString().isEmpty())) {
					Assert.fail(definition.getClass().getName()
							+ " does have a double defined look ahead pattern '"
							+ sourceToken.getLookAheadPatternString()
							+ "' defined!");
				}
			}
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

	@Test
	public void testKeywordCompleteness2() {
		try {
			List<TokenDefinition> tokens = Instances.createInstanceList(
					TokenDefinition.class, Fortran.getInstance().getKeywords());
			for (Class<?> clazz : keywords) {
				if (clazz.getName().endsWith("Test")) {
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
							+ " is not registered in FortranKeywords!");
				}
			}
		} catch (ClassInstantiationException e) {
			e.printStackTrace();
			Assert.fail("No exception was expected!");
		}
	}

}
