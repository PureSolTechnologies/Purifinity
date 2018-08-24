package com.puresoltechnologies.purifinity.server.plugin.fortran2008;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import org.junit.Ignore;
import org.junit.Test;

import com.puresoltechnologies.parsers.lexer.Token;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.source.SourceFileLocation;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.FortranPreConditioner;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.SourceForm;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.grammar.FortranGrammar;

public class FortranPreConditionerTest {

    private Pattern getPattern(String name) {
	try {
	    Field commentPattern = FortranPreConditioner.class.getDeclaredField(name);
	    commentPattern.setAccessible(true);
	    return (Pattern) commentPattern.get(0);
	} catch (SecurityException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	} catch (NoSuchFieldException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
	return null;
    }

    @Test
    public void testCommentPattern() {
	Pattern COMMENT_PATTERN = getPattern("FIXED_FORM_COMMENT_PATTERN");
	assertTrue(COMMENT_PATTERN.matcher("C A comment!").find());
	assertFalse(COMMENT_PATTERN.matcher(" C A comment!").find());

	assertTrue(COMMENT_PATTERN.matcher("c A comment!").find());
	assertFalse(COMMENT_PATTERN.matcher(" c A comment!").find());

	assertTrue(COMMENT_PATTERN.matcher("* A comment!").find());
	assertFalse(COMMENT_PATTERN.matcher(" * A comment!").find());

	assertTrue(COMMENT_PATTERN.matcher("! A comment!").find());
	assertTrue(COMMENT_PATTERN.matcher(" ! A comment!").find());
	assertTrue(COMMENT_PATTERN.matcher("  ! A comment!").find());
	assertTrue(COMMENT_PATTERN.matcher("   ! A comment!").find());
	assertTrue(COMMENT_PATTERN.matcher("    ! A comment!").find());
	assertFalse(COMMENT_PATTERN.matcher("     ! A comment!").find());
    }

    @Test
    public void testLabelPattern() {
	Pattern LABEL_PATTERN = getPattern("FIXED_FORM_LABEL_PATTERN");

	assertTrue(LABEL_PATTERN.matcher("1     ").find());
	assertTrue(LABEL_PATTERN.matcher(" 2    ").find());
	assertTrue(LABEL_PATTERN.matcher("  3   ").find());
	assertTrue(LABEL_PATTERN.matcher("   4  ").find());
	assertTrue(LABEL_PATTERN.matcher("    5 ").find());
	assertTrue(LABEL_PATTERN.matcher("    50").find());
	assertTrue(LABEL_PATTERN.matcher("12    ").find());
	assertTrue(LABEL_PATTERN.matcher(" 23   ").find());
	assertTrue(LABEL_PATTERN.matcher("  34  ").find());
	assertTrue(LABEL_PATTERN.matcher("   45 ").find());
	assertTrue(LABEL_PATTERN.matcher("   450").find());
	assertTrue(LABEL_PATTERN.matcher("123   ").find());
	assertTrue(LABEL_PATTERN.matcher(" 234  ").find());
	assertTrue(LABEL_PATTERN.matcher("  345 ").find());
	assertTrue(LABEL_PATTERN.matcher("  3450").find());
	assertTrue(LABEL_PATTERN.matcher("1234  ").find());
	assertTrue(LABEL_PATTERN.matcher(" 2345 ").find());
	assertTrue(LABEL_PATTERN.matcher(" 23450").find());
	assertTrue(LABEL_PATTERN.matcher("12345 ").find());
	assertTrue(LABEL_PATTERN.matcher("123450").find());

	assertFalse(LABEL_PATTERN.matcher("      ").find());
	assertFalse(LABEL_PATTERN.matcher("     0").find());
    }

    @Test
    public void testContinuationPattern() {
	Pattern CONTINUATION_PATTERN = getPattern("FIXED_FORM_CONTINUATION_PATTERN");
	assertTrue(CONTINUATION_PATTERN.matcher("     $").find());
	assertTrue(CONTINUATION_PATTERN.matcher("     *").find());
	assertFalse(CONTINUATION_PATTERN.matcher("     0").find());
    }

    @Test
    public void testEmptyPattern() {
	Pattern EMPTY_PATTERN = getPattern("FIXED_FORM_EMPTY_PATTERN");
	assertTrue(EMPTY_PATTERN.matcher("      ").find());
    }

    @Test
    public void testSingleQuoteLiteralEnd() {
	Pattern SINGLE_QUOTE_LITERAL_END = getPattern("FIXED_FORM_SINGLE_QUOTE_LITERAL_END");
	assertTrue(SINGLE_QUOTE_LITERAL_END.matcher("test'").find());
    }

    @Test
    public void testDoubleQuoteLiteralEnd() {
	Pattern DOUBLE_QUOTE_LITERAL_END = getPattern("FIXED_FORM_DOUBLE_QUOTE_LITERAL_END");
	assertTrue(DOUBLE_QUOTE_LITERAL_END.matcher("test\"").find());
    }

    @Test
    @Ignore
    public void testIsFixedForm() throws Exception {
	FortranPreConditioner fixedFormFile = new FortranPreConditioner(new SourceFileLocation(
		"src/test/resources/com/puresoltechnologies/purifinity/framework/lang/fortran2008/samples",
		"FixedFormSample.f").getSourceCode(), SourceForm.MIXED_FORM, true);
	assertTrue(fixedFormFile.isValidFixedForm());
    }

    @Test
    public void testScan() throws Exception {
	FortranPreConditioner fixedFormFile = new FortranPreConditioner(new SourceFileLocation(
		"src/test/resources/com/puresoltechnologies/purifinity/framework/lang/fortran2008/samples",
		"FixedFormSample.f").getSourceCode(), SourceForm.MIXED_FORM, true);
	TokenStream tokenStream = fixedFormFile.scan(FortranGrammar.getInstance().getLexer());
	for (Token token : tokenStream) {
	    System.out.print(token);
	    System.out.print("\t");
	    System.out.print(token.getVisibility());
	    System.out.print("\t");
	    System.out.println(token.getMetaData());
	}
	assertEquals("WHITESPACE", tokenStream.get(0).getName());
	assertEquals("COMMENT_LINE", tokenStream.get(6).getName());
    }
}
